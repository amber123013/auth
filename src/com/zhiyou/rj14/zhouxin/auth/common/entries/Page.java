package com.zhiyou.rj14.zhouxin.auth.common.entries;

public class Page {
	/**
	 * 页大小
	 */
	private int pageSize;
	/**
	 * 当前页码
	 */
	private int currPageNum;
	/**
	 * 总条数
	 */
	private int totalNum;
	/**
	 * 页大小
	 * @param pageSize
	 * @param currPageNum
	 */
	public Page(int pageSize){
		this.pageSize = pageSize > 0 ? pageSize : 10;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPageNum() {
		if(currPageNum <= 1){
			currPageNum = 1;
		}else if(currPageNum >= getTotalPage()){
			currPageNum = getTotalPage();
		}
		return currPageNum;
	}

	public void setCurrPageNum(int currPageNum) {
		this.currPageNum = currPageNum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	public int getStartNum(){
		return (getCurrPageNum() - 1) * pageSize;
	}
	
	/**
	 * 总页数
	 */
	public int getTotalPage() {
		int totalPage = totalNum / pageSize;
		if (totalNum % pageSize != 0) {
			totalPage += 1;
		}
		return totalPage;
	}

}
