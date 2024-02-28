#include "stdafx.h"
#include "Voice.h"

Voice::Voice(void)
{
}

Voice::~Voice(void)
{
    if (name != NULL)
    {
        LocalFree(name);
    }
}

void Voice::SetName(WCHAR* value)
{
    name = StrDupW(value);
}

WCHAR* Voice::GetName(void)
{
    return name;
}

