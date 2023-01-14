package com.consume.bhulekh.entity;

public class EncryptedData {
	
	private String  request_str;
  private  String request_token;
public EncryptedData() {
	super();
	// TODO Auto-generated constructor stub
}
public EncryptedData(String request_str, String request_token) {
	super();
	this.request_str = request_str;
	this.request_token = request_token;
}
public String getRequest_str() {
	return request_str;
}
public void setRequest_str(String request_str) {
	this.request_str = request_str;
}
public String getRequest_token() {
	return request_token;
}
public void setRequest_token(String request_token) {
	this.request_token = request_token;
}
  
  
  
  
  
  
  
}
