#include <stdafx.h>
#include "Synthesizer.h"
#include <log4cplus/loggingmacros.h>

//static initializations
log4cplus::Logger Synthesizer::logger =
    log4cplus::Logger::getInstance(_T("org.jvoicexml.sapi.cpp.Synthesizer"));
float Synthesizer::bytesPerSecond = 44100;

Synthesizer::Synthesizer(const wchar_t* engineName)
:cpVoice(NULL), hr(S_OK)
{
    HRESULT hr = CoCreateInstance(CLSID_SpVoice, NULL, CLSCTX_ALL, IID_ISpVoice,
        (void **)&cpVoice);

    // create cpEnum that contains the information about the designated TTSEngine
    CComPtr<IEnumSpObjectTokens> cpEnum;
    if(SUCCEEDED(hr))
    {	
        hr = SpEnumTokens(SPCAT_VOICES, NULL, NULL, &cpEnum);  
    }  

    // load required cpToken from cpEnum 
    // get the closest token if designated Voice is not traceable the system default
    // Voice is selected
    CComPtr<ISpObjectToken> cpToken;
    if(SUCCEEDED(hr))
    {
		ULONG ulNumTokens;

		// Get the numbers of tokens found
        hr = cpEnum->GetCount( &ulNumTokens );

        if ( SUCCEEDED( hr ) && 0 != ulNumTokens )
        {
			CSpDynamicString descriptionString;

            // Get the next token in the enumeration
            // State is maintained in the enumerator
            while (cpEnum->Next(1, &cpToken, NULL) == S_OK)
			{
				// Get a string which describes the token, in our case, the voice name
				hr = SpGetDescription( cpToken, &descriptionString);
				_ASSERTE( SUCCEEDED( hr ) );

				if (wcscmp(descriptionString, engineName) == 0) 
				{
					//Found voice
					break;					
				}

				cpToken.Release();
				descriptionString.Clear();
			}
			descriptionString.Clear();
		}
    }
    // Set the designated or system default Voice is used
    if(SUCCEEDED(hr))
    {    
        hr = cpVoice->SetVoice(cpToken);
    }

    // Set the notifyEvent
    if(SUCCEEDED(hr))
    {    
        /*hr = cpVoice->SetInterest(
            SPFEI(SPEI_WORD_BOUNDARY|SPEI_TTS_BOOKMARK|SPEI_PHONEME),SPFEI(SPEI_WORD_BOUNDARY
                |SPEI_TTS_BOOKMARK|SPEI_PHONEME));*/
		hr = cpVoice->SetInterest( SPFEI_ALL_TTS_EVENTS, SPFEI_ALL_TTS_EVENTS );
        hr = cpVoice->SetNotifyWin32Event();
    }

	LANGID m_LangID = 0;


	if (cpToken != NULL)
	{		
		CComPtr<ISpDataKey> cpAttribKey;
		hr = cpToken->OpenKey(L"Attributes", &cpAttribKey);		
		if(SUCCEEDED(hr))
		{
			//Get language code attribute (defined by Microsoft)
			WCHAR* pszLangID = NULL;
			hr = cpAttribKey->GetStringValue(L"Language", &pszLangID);
			if(SUCCEEDED(hr))
			{
				m_LangID = (unsigned short)wcstol(pszLangID, NULL, 16);
				::CoTaskMemFree(pszLangID);
			}

			cpAttribKey.Release();
		}
	}

	//Create phone converter
    hr = SpCreatePhoneConverter(m_LangID, NULL, NULL, &m_cpPhoneConv);

    cpToken.Release();
    cpEnum.Release();		
}

Synthesizer::~Synthesizer()
{
    if (cpVoice != NULL)
    {
        cpVoice->Release();
        cpVoice = NULL;
    }

	//Release phone converter
	m_cpPhoneConv.Release();
}

HRESULT Synthesizer::ListVoices(Voice*& voices, ULONG& num)
{
    CComPtr<IEnumSpObjectTokens> cpEnum;
    CComPtr<ISpObjectTokenCategory> cpVoiceCat;

    HRESULT hr = SpGetCategoryFromId(SPCAT_VOICES, &cpVoiceCat);
    if (FAILED(hr))
    {
        return hr;  
    }

    hr = cpVoiceCat->EnumTokens(NULL, NULL, &cpEnum);
    ISpObjectToken *pToken;
    cpEnum->GetCount(&num);
    voices = new Voice[num];
    ULONG i = 0;
    while (cpEnum->Next(1, &pToken, NULL)==S_OK)
    {
        CComPtr<ISpDataKey> cpAttribKey;
        hr=pToken->OpenKey(L"Attributes", &cpAttribKey);
        if (FAILED(hr))
        {
            continue;
        }
        WCHAR *wBuf = NULL;
        hr = cpAttribKey->GetStringValue(L"Name", &wBuf);
        if (SUCCEEDED(hr))
        {
            voices[i].SetName(wBuf);
            LOG4CPLUS_DEBUG(logger, wBuf);
            CoTaskMemFree(wBuf);
        }
        else
        {
            return hr;
        }

        WCHAR *keyName=NULL;
        int index=0;
        for(;;)
        {
            hr = cpAttribKey->EnumValues(index, &keyName);
            if (FAILED(hr))
            {
                if (hr == SPERR_NO_MORE_ITEMS)
                {
                    break;
                }
                break;
            }
            hr = cpAttribKey->GetStringValue(keyName, &wBuf);
            if (SUCCEEDED(hr))
            {
                LOG4CPLUS_DEBUG(logger, _T("   ") << keyName << _T(" = '")
                    << wBuf << _T("'"));
                CoTaskMemFree(wBuf);
            }
            else
            {
                LOG4CPLUS_DEBUG(logger, _T("   ") << keyName);
            }
            CoTaskMemFree(keyName);
            index++;
        }
        ++i;
    }
    return S_OK;
}

HRESULT Synthesizer::Speak(LPCWSTR speakString, bool isSSML, long& size, byte*& buffer, std::vector<std::wstring>& words, std::vector<float>& wordTimes, std::vector<std::pair<std::wstring, int>>& phoneInfos)
{
    // Create a stream to redirect the output.
    CComPtr<ISpeechMemoryStream> stream;
    hr = stream.CoCreateInstance(CLSID_SpMemoryStream);
    if(FAILED(hr))
	{
        return hr;
	}
    hr = cpVoice->SetOutput(stream, TRUE);
    if (FAILED(hr))
    {
        return hr;
    }

	// Start rendering text asynchronously
	hr = cpVoice->Speak(speakString, SPF_ASYNC
            | (isSSML ? SPF_IS_XML : SPF_IS_NOT_XML), NULL);
    if (FAILED(hr))
    {
        return hr;
    }

	//Consume all requested events that will be produced while synthesizing
	CSpEvent event;
	bool stop = false;

	while (!stop)
    {
		//Wait for event generated while synthesizing 
		cpVoice->WaitForNotifyEvent(50);

		//Get event
		hr = event.GetFrom(cpVoice);
		if (hr == S_OK)
		{
			switch( event.eEventId )
			{
				case SPEI_END_INPUT_STREAM:
					//Synthesis has finished
					stop = true;
					break;
				case SPEI_START_INPUT_STREAM:
					break;
				case SPEI_PHONEME:
					{
						//Extract duration from wParam
						int duration = event.wParam >> (sizeof(WORD) * 8);
						
						//Convert phone ID to IPA
						WCHAR ipaPhoneWChar[SP_MAX_PRON_LENGTH];
						ipaPhoneWChar[0] = L'\0';
						SPPHONEID phoneId[2];
						phoneId[0] = event.Phoneme();
						phoneId[1] = 0x0;
						m_cpPhoneConv->IdToPhone(phoneId, ipaPhoneWChar);

						//Create pair
						std::wstring phone(ipaPhoneWChar);
						std::pair<std::wstring, int> phoneInfo =
                            std::make_pair(phone, duration);
						phoneInfos.push_back(phoneInfo);
						LOG4CPLUS_TRACE(logger, _T("Phoneme '")
                            << ipaPhoneWChar << _T("' id: ")
                            << (int) event.Phoneme() << _T(" duration: ")
                            << duration);
					}
					break;
				case SPEI_WORD_BOUNDARY:
					{
						std::wstring aaaa(speakString);
						std::wstring word =	aaaa.substr(event.lParam, event.wParam);
			
						//Store word
						words.push_back(word);

						//And also its start time
						wordTimes.push_back(event.ullAudioStreamOffset / bytesPerSecond);
                        LOG4CPLUS_DEBUG(logger, _T("Word '") << speakString << _T("' ")
                            << (float) event.ullAudioStreamOffset / bytesPerSecond);
					}
					break;				 
			};
		}

		//Check TTS status
		SPVOICESTATUS voiceStatus;
		hr = cpVoice->GetStatus(&voiceStatus, NULL);
		if (hr = S_OK) {
			if (voiceStatus.dwRunningState == SPRS_DONE) stop = true;
		}

	};

    // Retrieve the data from the stream and pass it back to the caller.
    VARIANT c;
    VariantInit(&c); 
    hr = stream->GetData(&c);
    if (FAILED(hr))
    {
        return hr;
    }
    SAFEARRAY* sa = c.parray;
	long lstart;
	long lend;
	SafeArrayGetLBound(sa, 1, &lstart);
	SafeArrayGetUBound(sa, 1, &lend);
	size = lend - lstart + 1;

    buffer = new byte[size];
    memcpy(buffer, sa->pvData, size * sizeof(byte));
    return S_OK;
}

HRESULT Synthesizer::GetAudioFormat(WAVEFORMATEX& f)
{
    CComPtr<ISpeechMemoryStream> stream;
    HRESULT hr = stream.CoCreateInstance(CLSID_SpMemoryStream);
    if(FAILED(hr))
	{
        return hr;
	}

    CComPtr<ISpeechAudioFormat> format;
    if(FAILED(hr))
	{
        return hr;
	}
    hr = stream->get_Format(&format);
    if(FAILED(hr))
	{
        return hr;
	}
    CComPtr<ISpeechWaveFormatEx> waveFormat;
    if(FAILED(hr))
	{
        return hr;
	}
    hr = format->GetWaveFormatEx(&waveFormat);
    if(FAILED(hr))
	{
        return hr;
	}
    
    waveFormat->get_AvgBytesPerSec((long*)&f.nAvgBytesPerSec);
    waveFormat->get_BitsPerSample((short*)&f.wBitsPerSample);
    waveFormat->get_Channels((short*)&f.nChannels);
    waveFormat->get_FormatTag((short*)&f.wFormatTag);
    waveFormat->get_SamplesPerSec((long*)&f.nSamplesPerSec);
    return S_OK;
}

