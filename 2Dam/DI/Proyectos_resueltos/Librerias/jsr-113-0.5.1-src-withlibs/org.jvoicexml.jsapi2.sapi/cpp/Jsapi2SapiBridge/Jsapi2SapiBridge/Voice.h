#pragma once

class Voice
{
public:
    Voice(void);
    ~Voice(void);

    void SetName(WCHAR* name);
    WCHAR* GetName(void);

private:
    WCHAR* name;
};
