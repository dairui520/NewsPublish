package com.cdy.javabean;

public class PageBean {

	/// <summary>
    /// ����ĵ�ǰҳ
    /// </summary>
    public  int CurrentPage ;

    /// <summary>
    /// ÿһҳ��ʾ����Ŀ
    /// </summary>
    public int NumPerPage ;


    /// <summary>
    /// ��ѯ����
    /// </summary>
    public String SearchData ;

    /// <summary>
    /// ��ҳ��
    /// </summary>
    public int TotlaPage ;
    
    
    public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}

	public int getNumPerPage() {
		return NumPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		NumPerPage = numPerPage;
	}

	public String getSearchData() {
		return SearchData;
	}

	public void setSearchData(String searchData) {
		SearchData = searchData;
	}

	public int getTotlaPage() {
		return TotlaPage;
	}

	public void setTotlaPage(int totlaPage) {
		TotlaPage = totlaPage;
	}

	
}
