#pragma once

#include <jni.h>
#include <vector>
#include <map>
#include <string>
#include <log4cplus/logger.h>

class Recognizer
{
public:

    Recognizer(HWND hwnd, JNIEnv *env, jobject rec);
    virtual ~Recognizer();

	HRESULT SetRecognizerInputStream(CComPtr<ISpStream> spStream);
    static HRESULT GetAudioFormat(WAVEFORMATEX& format);

    HRESULT Resume();

    HRESULT Pause();

    HRESULT LoadGrammar(const wchar_t* grammar,LPCWSTR grammarID);

    HRESULT LoadGrammarFile(LPCWSTR grammarPath, LPCWSTR grammarID);
	
	HRESULT DeleteGrammar(LPCWSTR ID);

    HRESULT RecognitionHappened(WCHAR* recoResult[]);
	
	HRESULT StartRecognition(WCHAR* result[]);

	HRESULT AbortRecognition();

    HRESULT	hr;

private:

	boolean continuing;

    void Recognized(LPWSTR utterance);

	HRESULT BlockForResult(ISpRecoGrammar* cpGrammar, ISpRecoResult ** ppResult);
	
	std::map< std::wstring , CComPtr<ISpRecoGrammar> > gramHash;

    CComPtr<ISpRecognizer>		cpRecognizerEngine;

    CComPtr<ISpRecoContext>		cpRecoCtxt;

    JNIEnv* jenv;

    jobject jrec;

    /** Logger instance. */
    static log4cplus::Logger logger;
};

