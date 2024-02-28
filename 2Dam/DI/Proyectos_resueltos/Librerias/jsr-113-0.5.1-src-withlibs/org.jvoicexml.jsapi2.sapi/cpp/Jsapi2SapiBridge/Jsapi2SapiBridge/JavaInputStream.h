#pragma once

#include <initguid.h>
#include <windows.h>
#include <jni.h>

#include <log4cplus/loggingmacros.h>
#include <log4cplus/logger.h>

extern JavaVM *jvm;

// %%GUIDs: ------------------------------------------------------------------
DEFINE_GUID(CLSID_JavaInputStream, 0x5e9ddec7, 0x5767, 0x11cf, 0xbe, 0xab, 0x0, 0xaa, 0x0, 0x6c, 0x36, 0x6);

// {F1D3E19D-8D32-4ef6-A656-99E242E31098}
DEFINE_GUID(IID_IJavaInputStream,0xf1d3e19d, 0x8d32, 0x4ef6, 0xa6, 0x56, 0x99, 0xe2, 0x42, 0xe3, 0x10, 0x98);


class InputStream : public IStream
{
  public:
	// IUnknown
	virtual STDMETHODIMP QueryInterface (REFIID riid, void** ppv) 
    {
		return 0;
    };
    virtual STDMETHODIMP_(ULONG) AddRef(void) 
    {
		return 1;
    };
    virtual STDMETHODIMP_(ULONG) Release(void) 
    {
		return 1;
    };

	// IJavaInputStream
	virtual STDMETHODIMP SetJavaInputStream(JNIEnv *env, jobject object) 
	{
		return S_OK;
	};
  public:
    JNIEnv*		env;		// jvm-env holding our InputStream
	jobject		inputStream;	// the Java InputStream
	jmethodID	jReadByteArray;	// methodID of "int read(byte[], int, int)"
	jmethodID	jReadByte;		// methodID of "int read()"
	jmethodID	jAvailable;		// methodID of "int available()"
};

// Java streaming class supporting a dummy IStream
class JavaInputStream : public InputStream 
{
  public:
    // IUnknown
    STDMETHODIMP    QueryInterface (REFIID iid, void **ppv);
	STDMETHODIMP_(ULONG) AddRef(void) 
    {
        return InterlockedIncrement(&ref); 
    };
	STDMETHODIMP_(ULONG) Release(void)
    {
        if (InterlockedDecrement(&ref) == 0)
        {
            // Ouch!
            // TODO Need a better solution
            delete this;
            return 0;
        }
        return 1;
    }

    // IStream
    STDMETHODIMP    Read(void *pv, ULONG cb, ULONG *pcbRead);
    STDMETHODIMP    Write(VOID const *pv, ULONG cb, ULONG *pcbWritten)
    {
		return E_FAIL;
    };
    STDMETHODIMP    Seek(LARGE_INTEGER dbMove, DWORD dwOrigin, ULARGE_INTEGER *pbNewPosition)
	{
		if (dbMove.HighPart == 0 && dbMove.LowPart == 0)
        {
			return S_OK;
        }
		else
        {
			return E_FAIL;
        }
    };
    STDMETHODIMP    SetSize(ULARGE_INTEGER cbNewSize)
    {
		return E_FAIL;
    };
    STDMETHODIMP    CopyTo(IStream *pstm, ULARGE_INTEGER cb, ULARGE_INTEGER *pcbRead, ULARGE_INTEGER *pcbWritten)
    {
		return E_FAIL;
    };
    STDMETHODIMP    Commit(DWORD grfCommitFlags)
    {
		return E_FAIL;
    };
    STDMETHODIMP    Revert(void)
    {
		return E_FAIL;
    };
    STDMETHODIMP    LockRegion(ULARGE_INTEGER bOffset, ULARGE_INTEGER cb, DWORD dwLockType)
    {
		return E_FAIL;
    };
    STDMETHODIMP    UnlockRegion(ULARGE_INTEGER bOffset, ULARGE_INTEGER cb, DWORD dwLockType)
    {
		return E_FAIL;
    };
    STDMETHODIMP    Stat(STATSTG *pstatstg, DWORD grfStatFlag)
    {
		return E_FAIL;
    };
    STDMETHODIMP    Clone(IStream **ppstm)
    {
		return E_FAIL;
    };

	// JavaInputStream
	STDMETHODIMP SetJavaInputStream(JNIEnv *env, jobject object);
    // constructors/destructors
    JavaInputStream()     
	{ 
		ref = 0; 
		env = NULL;
	}
	virtual ~JavaInputStream();

  private:
    /** Reference xounter */
    LONG        ref;

    /** Logger instance. */
    static log4cplus::Logger logger;
};