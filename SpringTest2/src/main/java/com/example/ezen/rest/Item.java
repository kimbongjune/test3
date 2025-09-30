package com.example.ezen.rest;

//공공데이터 기상청 예보 응답데이터(item)
public class Item {
	private String baseDate;
	private String baseTime;
	private String category;
	private int nx;
	private int ny;
	private String obsrValue;
	
	public String getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	public String getBaseTime() {
		return baseTime;
	}
	public void setBaseTime(String baseTime) {
		this.baseTime = baseTime;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNx() {
		return nx;
	}
	public void setNx(int nx) {
		this.nx = nx;
	}
	public int getNy() {
		return ny;
	}
	public void setNy(int ny) {
		this.ny = ny;
	}
	public String getObsrValue() {
		return obsrValue;
	}
	public void setObsrValue(String obsrValue) {
		this.obsrValue = obsrValue;
	}
	@Override
	public String toString() {
		return "Item [baseDate=" + baseDate + ", baseTime=" + baseTime + ", category=" + category + ", nx=" + nx
				+ ", ny=" + ny + ", obsrValue=" + obsrValue + "]";
	}
	
	
}
