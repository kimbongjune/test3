package com.example.ezen.rest;

public class Body {
	private String dataType;
	private int pageNo;
	private int numOfRows;
	private int totalCount;
	private Items items;
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Body [dataType=" + dataType + ", pageNo=" + pageNo + ", numOfRows=" + numOfRows + ", totalCount="
				+ totalCount + ", items=" + items + "]";
	}
	
	
}
