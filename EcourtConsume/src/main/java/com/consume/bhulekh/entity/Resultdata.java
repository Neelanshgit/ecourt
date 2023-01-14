package com.consume.bhulekh.entity;

import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Resultdata {

	
	private String state_code;
	
	private String dist_code;
	//@JsonProperty("land_details")
	
	private List<CaseData>  land_details;

	public Resultdata() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resultdata(String state_code, String dist_code, List<CaseData> land_details) {
		super();
		this.state_code = state_code;
		this.dist_code = dist_code;
		this.land_details = land_details;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getDist_code() {
		return dist_code;
	}

	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
	}
	@JsonAnyGetter
	public List<CaseData> getLand_details() {
		return land_details;
	}
 	@JsonAnySetter
	public void setLand_details(List<CaseData> land_details) {
		this.land_details = land_details;
	}

	@Override
	public String toString() {
		return "Resultdata [state_code=" + state_code + ", dist_code=" + dist_code + ", land_details=" + land_details
				+ "]";
	}
	
	
}
