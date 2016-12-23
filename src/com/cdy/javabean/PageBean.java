package com.cdy.javabean;

public class PageBean {

	/// <summary>
    /// 请求的当前页
    /// </summary>
    public  int CurrentPage ;

    /// <summary>
    /// 每一页显示的条目
    /// </summary>
    public int NumPerPage ;


    /// <summary>
    /// 查询条件
    /// </summary>
    public String SearchData ;

    /// <summary>
    /// 总页数
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
