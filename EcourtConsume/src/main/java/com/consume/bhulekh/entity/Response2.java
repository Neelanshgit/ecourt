package com.consume.bhulekh.entity;

public class Response2 {
	
	private String response_str;
	
	private String response_token;
	private String version;
	public Response2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Response2(String response_str, String response_token, String version) {
		super();
		this.response_str = response_str;
		this.response_token = response_token;
		this.version = version;
	}
	public String getResponse_str() {
		return response_str;
	}
	public void setResponse_str(String response_str) {
		this.response_str = response_str;
	}
	public String getResponse_token() {
		return response_token;
	}
	public void setResponse_token(String response_token) {
		this.response_token = response_token;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	@Override
	public String toString() {
		return "Response2 [response_str=" + response_str + ", response_token=" + response_token + ", version=" + version
				+ "]";
	}
	
	
}
