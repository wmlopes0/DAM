//**************************************************************************
// CHashtable
//**************************************************************************

template<class TKey, class TValue>
class CHashtable {
  protected:

  template<class TKey, class TValue>
  class CHashvalue {
    public:
    TKey m_hKey;
    TValue m_hValue;
    
    CHashvalue() {}
    CHashvalue(const TKey& hKey, const TValue& hValue) {
      m_hKey = hKey;
      m_hValue = hValue; 
    }
  };
  
  private:

  CList< CHashvalue< TKey, TValue> > m_hObList;  
  TValue m_hValue_Null;
  
  //-Initialisierung-------------------------------------------------------

  public:
  CHashtable(const TValue& hValue_Null) { m_hValue_Null = hValue_Null; }


  //-Funktionen------------------------------------------------------------

  void Add(const TKey& hKey, const TValue& hValue)  { m_hObList.AddTail(CHashvalue<TKey, TValue>(hKey, hValue)); }
  void Clear()  { m_hObList.RemoveAll(); }
  void Remove(const TKey& hKey);
  BOOL ContainsKey(const TKey& hKey);
  TValue GetValue(const TKey& hKey);
  POSITION GetHeadPosition()  { return m_hObList.GetHeadPosition(); }
  TValue GetNext(POSITION& rPosition)  { return m_hObList.GetNext(rPosition).m_hValue ; }
  TKey GetKey(POSITION& rPosition)  { return m_hObList.GetNext(rPosition).m_hKey ; }
  Int4 GetCount()  { return m_hObList.GetCount(); }
};


