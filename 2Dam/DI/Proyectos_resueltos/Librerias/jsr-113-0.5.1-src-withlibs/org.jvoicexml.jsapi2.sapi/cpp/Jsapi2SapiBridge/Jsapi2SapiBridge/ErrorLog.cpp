#include "ErrorLog.h"


ErrorLog::ErrorLog()
	: lastError(NULL)
{
}

ErrorLog::~ErrorLog()
{
	if (lastError != NULL)
	{
		delete[] lastError;
		lastError = NULL;
	}
}



STDMETHODIMP_(ULONG) ErrorLog::AddRef()
{
	return ++refCount; // Increment this object's reference count.
}

STDMETHODIMP_(ULONG) ErrorLog::Release()
{
	ULONG uRet = --refCount; // Decrement this object's reference count.
	if ( refCount == 0 ) // Releasing last reference?
	{
		delete this;
	}

	return uRet;
}

STDMETHODIMP ErrorLog::QueryInterface ( REFIID riid, void** ppv )
{
    if (ppv == NULL)
    {
        return E_INVALIDARG;
    }
	if (riid == IID_IUnknown)
    {
		*ppv = static_cast<IUnknown*> (this);
        AddRef();
        return S_OK;
    }

	if (riid == IID_ISpErrorLog)
    {
		*ppv = static_cast<ISpErrorLog*> (this);
		AddRef();
		return S_OK;
	}

	if (riid == IID_IErrorLog)
    {
		*ppv = static_cast<ErrorLog*> (this);
		AddRef();
		return S_OK;
	}

    *ppv = NULL;
    return E_NOINTERFACE;
}


void STDMETHODCALLTYPE ErrorLog::ClearErrors()
{
	errors.clear();
}


HRESULT ErrorLog::AddError(const long lLineNumber,
						   HRESULT hr,
						   const WCHAR *pszDescription,
						   const WCHAR *pszHelpFile,
						   DWORD dwHelpContext)
{
	//std::string errorContent = FormattedString("Error %d at line %d of %s: %s",
	//	hr, lLineNumber, (pszHelpFile !=NULL) ? ConvertFromWide((WCHAR*)pszHelpFile).c_str() : "UNKNOWN",
	//	(pszDescription!=NULL)?ConvertFromWide((WCHAR*)pszDescription).c_str():"EMP­TY");

	//m_errors.push_back(errorContent);
	const size_t sizeDescription = wcslen(pszDescription) + 1;
	lastError = new wchar_t[sizeDescription];
	wcsncpy(lastError, pszDescription, sizeDescription);

	return S_OK;
}


