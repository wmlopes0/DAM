#pragma once

//STL includes
#include <vector>
#include <string>
#include <utility>

#include "Voice.h"
#include <log4cplus/logger.h>

class Synthesizer
{
public:
	Synthesizer(const wchar_t* engineName);
    virtual ~Synthesizer();

	HRESULT Speak(LPCWSTR speakString, bool isSSML, long& size, byte*& buffer, std::vector<std::wstring>& words, std::vector<float>& wordTimes, std::vector< std::pair<std::wstring, int> >& phoneInfos);
    static HRESULT GetAudioFormat(WAVEFORMATEX& format);

    static HRESULT Synthesizer::ListVoices(Voice*& voices, ULONG& num);

	static void setBytesPerSecond(float bps) { Synthesizer::bytesPerSecond = bps; };

    boolean Cancel(){
		if (SUCCEEDED( cpVoice->Speak(NULL, SPF_PURGEBEFORESPEAK, NULL)))
        {
			return true;
		}
		else 
        {
			return false;
		}			
	}

	boolean Pause(){
		if(  SUCCEEDED( hr = cpVoice->Pause() )  )
        {
			return true;
		}
		else 
        {
			return false;
		}	
	}

	boolean Resume()
    {
		if(  SUCCEEDED( cpVoice->Resume() )  )
        {
			return true;
		}
		else 
        {
			return false;
		}	
	}

	HANDLE getEventHandler()
    {	
		return cpVoice->GetNotifyEventHandle();
	}

    HRESULT GetLastHResult()
    {
        return hr;
    }
private:					
    HRESULT	hr;
    ISpVoice* cpVoice;

	static float bytesPerSecond;

	CComPtr<ISpPhoneConverter>      m_cpPhoneConv;                  // Phone converter

	std::string to_str( const std::wstring& wstr )
	{
		size_t size = wstr.length();
		char* s = new char[size+1];

		memset( s, 0, sizeof(char) * (size+1) );

		WideCharToMultiByte(CP_ACP, 0, wstr.c_str(), size, s, size, NULL, NULL);

		std::string str(s);
		delete[] s;
		return str;
	}

    /** Logger instance. */
    static log4cplus::Logger logger;
};
