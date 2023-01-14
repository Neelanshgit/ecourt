package com.consume.bhulekh.entity;

public class Response1 {
	
	private String access_token;
	private String refresh_token;
	private String scope;
	private String token_type;
	
	
	
	
	public Response1() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Response1(String access_token, String refresh_token, String scope, String token_type) {
		super();
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.scope = scope;
		this.token_type = token_type;
	}




	public String getAccess_token() {
		return access_token;
	}




	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}




	public String getRefresh_token() {
		return refresh_token;
	}




	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}




	public String getScope() {
		return scope;
	}




	public void setScope(String scope) {
		this.scope = scope;
	}




	public String getToken_type() {
		return token_type;
	}




	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	
	
	
	

}
