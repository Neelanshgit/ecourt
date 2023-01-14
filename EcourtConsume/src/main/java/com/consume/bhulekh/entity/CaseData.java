package com.consume.bhulekh.entity;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;





@JsonAutoDetect
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseData {

	private String cino;
		
	private Map<String,List<Party>> map = new LinkedHashMap<>();
	public CaseData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CaseData(String cino, Map<String, List<Party>> map) {
		super();
		this.cino = cino;
		this.map = map;
	}
	public String getCino() {
		return cino;
	}
	public void setCino(String cino) {
		this.cino = cino;
	}
	@JsonAnyGetter
	public Map<String,  List<Party>> any(){
	    return this.map;
		
		
	}
	//@JsonAnyGetter
	public Map<String, List<Party>> getMap() {
		return map;
	}
	@JsonAnySetter
	public void  setMap(String key, List<Party> value) {
		
		map.put(key, value);
	}
	
	
//	public void setMap(Map<String, List<Party>> map) {
//		this.map = map;
//	}
	@Override
	public String toString() {
		return "CaseData [cino=" + cino + ", map=" + map + "]";
	}
	
	
	
	
	
	

}
