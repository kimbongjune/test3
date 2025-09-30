package com.example.ezen;

//ajax요청에 대한 기본적인 응답을 담는 클래스
public class Response {
	private String result;
	private int code;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
