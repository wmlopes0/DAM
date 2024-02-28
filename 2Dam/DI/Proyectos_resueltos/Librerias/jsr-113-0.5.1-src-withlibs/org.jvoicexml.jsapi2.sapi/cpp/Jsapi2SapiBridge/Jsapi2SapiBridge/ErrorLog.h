#pragma once

#include <vector>
#include <string>
#include "sapi.h"
#include "sapiddk.h"

// {224DE0DA-16F8-4DFC-A37C-95F5E99F998D}
DEFINE_GUID(IID_IErrorLog, 0x224de0da, 0x16f8, 0x4dfc, 0xa3, 0x7c, 0x95, 0xf5, 0xe9, 0x9f, 0x99, 0x8d);

// {E16372E1-3DE2-4132-865B-8E1F6201B3BF}
DEFINE_GUID(CLSID_ErrorLog, 0xe16372e1, 0x3de2, 0x4132, 0x86, 0x5b, 0x8e, 0x1f, 0x62, 0x1, 0xb3, 0xbf);


class ErrorLog: public ISpErrorLog
{
public:
	ErrorLog();
	virtual ~ErrorLog();


	// IUnknown
	STDMETHOD_(ULONG, AddRef)();
	STDMETHOD_(ULONG, Release)();
	STDMETHOD(QueryInterface)(REFIID riid, void** ppv);


	HRESULT STDMETHODCALLTYPE AddError(const long
		lLineNumber,
		HRESULT hr,
		const WCHAR *pszDescription,
		const WCHAR *pszHelpFile,
		DWORD dwHelpContext);

	STDMETHOD_(VOID, ClearErrors());

private:
	std::vector<std::string> errors;
	ULONG refCount;
	wchar_t* lastError;
};




