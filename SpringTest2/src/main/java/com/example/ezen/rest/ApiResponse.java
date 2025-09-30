package com.example.ezen.rest;

public class ApiResponse {
	private Response response;

	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "ApiResponse [response=" + response + "]";
	}
}
