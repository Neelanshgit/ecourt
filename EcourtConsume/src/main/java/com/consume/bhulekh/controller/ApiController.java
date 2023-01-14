package com.consume.bhulekh.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.consume.bhulekh.entity.CaseData;
import com.consume.bhulekh.entity.EncryptedData;
import com.consume.bhulekh.entity.Party;
import com.consume.bhulekh.entity.Response1;
import com.consume.bhulekh.entity.Resultdata;
import com.consume.bhulekh.entity.Dao.PartyDao;
import com.consume.bhulekh.service.Decryptor;
import com.consume.bhulekh.service.Encryptor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ApiController {
	private static  String url1 = "http://164.100.58.148/ST1/air";
	private static String url2="https://restcountries.eu/rest/v2/all";
	private static String url3="http://164.100.58.148/ST1/test2?eid={eid}";
	
	//private static String e1="https://egw.bharatapi.gov.in:443/token?grant_type={grant}&username={username}&password={password}&scope={scope}";
	private static String e1="https://egw.bharatapi.gov.in/token?grant_type={grant}&username={username}&password={password}&scope={scope}";
	private static String e2="https://egw.bharatapi.gov.in/t/ecourts.gov.in/land_details/v1.0/landSurveyDetails?dept_id={dept_id}&request_str={request_str}&request_token={request_token}&version={version}";
	//private static String e3="https://egw.bharatapi.gov.in/t/ecourts.gov.in/land_details/v1.0/landSurveyDetails?dept_id=SE00021&request_str=ebG2dm6S4k221IYlTwD5rRq6CvM8Es4f%2FAEpJBgYolOUV9mVGTLwIz2TJ7VPdnKc5Vpvy4HXQFo2kHYoGaBQJp%2Bount%2B9XhDb0ki0COYrdw%3D&request_token=5ecdc92f1d02bab7b2aa59e12307fb824d57424978bab196c0830c1cbcd257b9&version=v1.0";
	private static String c1="https://egw.bharatapi.gov.in/t/ecourts.gov.in/CNR_Search/v1.0/cnrFullCaseDetails?dept_id={dept_id}&request_str={request_str}&request_token={request_token}&version={version}";
	
	
	@Autowired 
	 private RestTemplate rest,r2;
	@Autowired
	private Encryptor encrpt;
	@Autowired
	private Decryptor dcrpt;
	
	// for my test
	@RequestMapping("/testair")
	@ResponseBody
	public String h() {
		
		return "running well";
		
		
	}
// first click return 
@CrossOrigin(origins = "*")
@RequestMapping(value ="/ecourt3")
@ResponseBody
public List<PartyDao> getEcourt3(@RequestParam String village_code,@RequestParam String dcc,@RequestParam String tcc,@RequestParam String khasra_no) throws UnsupportedEncodingException{
	
	String grant ="password";
	String username ="borlko@ecourts.gov.in";
	String password ="test@123";
	String scope ="''";
	//"844";"815";"142067"
	HttpHeaders headers = new HttpHeaders();
	headers.add("Authorization", "Basic " + "YTVmcW9LSVBnRnV3YkU4ZHR4VVU5cVFYWF9NYTpWVnJIN3BpdllSOW5IMmhYSFVkZlpEMFp2eGth");
	headers.add("Content-Type","application/x-www-form-urlencoded");
	HttpEntity<String> request = new HttpEntity<String>(headers);
	ResponseEntity<Response1> response = rest.exchange(e1, HttpMethod.POST,request, Response1.class,grant,username,password,scope);
	//response recieved for first time 
    Response1 r= response.getBody();
	//System.out.println(r.getAccess_token());
	String auth_token=r.getAccess_token();
	//System.out.println("auth->"+auth_token);
	EncryptedData en=	encrpt.encryptresponse(auth_token,dcc,tcc,village_code,khasra_no);	
	//System.out.println(en.getRequest_str());
	//System.out.println(en.getRequest_token());
	
	//RestTemplate r2= new RestTemplate();
	String dept_id="SE00021";
	String request_str=en.getRequest_str();
	String request_token=en.getRequest_token();
	String version="v1.0";
	//System.out.println("request_str->"+request_str);
	//System.out.println("request_token->"+request_token);
	HttpHeaders headers2 = new HttpHeaders();
	headers2.add("Authorization","Bearer "+auth_token);
	HttpEntity<String> request2 = new HttpEntity<String>(headers2);
	MultiValueMap< String,String> params =  new LinkedMultiValueMap<>();
	params.add("dept_id","SE00021");
	try {
		params.add("request_str", URLEncoder.encode(en.getRequest_str(), "UTF-8"));
	} catch (UnsupportedEncodingException e) {
	
		e.printStackTrace();
	}
	params.add("request_token", en.getRequest_token());
	params.add("version", "v1.0");
	
	
	UriComponentsBuilder   builder = UriComponentsBuilder.fromUriString("https://egw.bharatapi.gov.in/t/ecourts.gov.in/land_details/v1.0/landSurveyDetails").queryParams(params).encode();
	URI uri = builder.build(true).toUri();
	ResponseEntity<String> response2 = r2.exchange(uri, HttpMethod.GET,request2,String.class);
    Object o= (Object) response2.getBody();
    String S= response2.getBody();

	JSONObject jsnobject = new JSONObject(S);
	String dild = jsnobject.get("response_str").toString();
	String result = dcrpt.decrypt(dild.getBytes());
    String result3 = result.substring(1,result.length()-1 );
     int count =1;
	List<PartyDao> preturn = new ArrayList<>();
	try {
	ObjectMapper mapper = new ObjectMapper();
	// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	mapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);
	Resultdata c=	mapper.readValue(result3,Resultdata.class);
	List<CaseData> cd = c.getLand_details();
	Map<String, List<Party>> party = new LinkedHashMap<String, List<Party>>();
	List<Party> list = new ArrayList<Party>();
	String cinoStore = new String();
	
		for(CaseData data : cd) {
		cinoStore =data.getCino();
		party= data.getMap();
		list =party.get(cinoStore);
		int setter=0;
		for(Party l: list) {
			 //System.out.println(l.getParty_name());
			 preturn.add(setter,new PartyDao(cinoStore,l.getItem_no(),l.getKhata_number(),l.getArea(),l.getSurvey_no(),l.getParty_name(),l.getTaluka_code(),l.getVillage_code())); 
			setter++;
		}
    count++;
 
      }
	   } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getClass().getName() + " : "
			        + e.getOriginalMessage());
			}
  
	return preturn;
//	return "success";
}
// cino data 
	


@CrossOrigin(origins = "*")
@RequestMapping(value ="/getCINOtest2")
@ResponseBody
public String getCINOtest2(@RequestParam String cino) throws UnsupportedEncodingException{
	
	String grant ="password";
	String username ="borlko@ecourts.gov.in";
	String password ="test@123";
	String scope ="''";
	
  //  String cin= "UPUN010009912016";
	HttpHeaders headers = new HttpHeaders();
	headers.add("Authorization", "Basic " + "YTVmcW9LSVBnRnV3YkU4ZHR4VVU5cVFYWF9NYTpWVnJIN3BpdllSOW5IMmhYSFVkZlpEMFp2eGth");
	headers.add("Content-Type","application/x-www-form-urlencoded");
	HttpEntity<String> request = new HttpEntity<String>(headers);
	ResponseEntity<Response1> response = rest.exchange(e1, HttpMethod.POST,request, Response1.class,grant,username,password,scope);
	//response recieved for first time 
    
	Response1 r= response.getBody();
	System.out.println(r.getAccess_token());
	String auth_token=r.getAccess_token();
	System.out.println("auth->"+auth_token);
	EncryptedData en=	encrpt.encryptresponse(auth_token,cino);	
	//System.out.println(en.getRequest_str());
	//System.out.println(en.getRequest_token());
	
	//RestTemplate r2= new RestTemplate();
	String dept_id="SE00021";
	String request_str=en.getRequest_str();
	String request_token=en.getRequest_token();
	String version="v1.0";
	//System.out.println("request_str->"+request_str);
	//System.out.println("request_token->"+request_token);
	HttpHeaders headers2 = new HttpHeaders();
	headers2.add("Authorization","Bearer "+auth_token);
	HttpEntity<String> request2 = new HttpEntity<String>(headers2);
	MultiValueMap< String,String> params =  new LinkedMultiValueMap<>();
	params.add("dept_id","SE00021");
	try {
		params.add("request_str", URLEncoder.encode(en.getRequest_str(), "UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	params.add("request_token", en.getRequest_token());
	params.add("version", "v1.0");
	UriComponentsBuilder   builder = UriComponentsBuilder.fromUriString("https://egw.bharatapi.gov.in/t/ecourts.gov.in/CurrentStatus/v1.0/currentStatus").queryParams(params).encode();
	URI uri = builder.build(true).toUri();
	ResponseEntity<String> response2 = r2.exchange(uri, HttpMethod.POST,request2,String.class);
	//System.out.println("success1first");
	Object o= (Object) response2.getBody();
    String S= response2.getBody();
   	JSONObject jsnobject = new JSONObject(S);
	String dild = jsnobject.get("response_str").toString();
  
    String result = dcrpt.decrypt(dild.getBytes());
    return result;
//	return "success";
}
	


@CrossOrigin(origins = "*")
@RequestMapping(value ="/getCINOFull")
@ResponseBody
public String getCINOtestFull(@RequestParam String cino) throws UnsupportedEncodingException{
	
	String grant ="password";
	String username ="borlko@ecourts.gov.in";
	String password ="test@123";
	String scope ="''";
	HttpHeaders headers = new HttpHeaders();
	headers.add("Authorization", "Basic " + "YTVmcW9LSVBnRnV3YkU4ZHR4VVU5cVFYWF9NYTpWVnJIN3BpdllSOW5IMmhYSFVkZlpEMFp2eGth");
	headers.add("Content-Type","application/x-www-form-urlencoded");
	HttpEntity<String> request = new HttpEntity<String>(headers);
	ResponseEntity<Response1> response = rest.exchange(e1, HttpMethod.POST,request, Response1.class,grant,username,password,scope);
	//response recieved for first time 
    
	Response1 r= response.getBody();
	//System.out.println(r.getAccess_token());
	String auth_token=r.getAccess_token();
	//System.out.println("auth->"+auth_token);
	EncryptedData en=	encrpt.encryptresponse(auth_token,cino);	
	//System.out.println(en.getRequest_str());
	//System.out.println(en.getRequest_token());
	
	//RestTemplate r2= new RestTemplate();
	String dept_id="SE00021";
	String request_str=en.getRequest_str();
	String request_token=en.getRequest_token();
	String version="v1.0";
	//System.out.println("request_str->"+request_str);
	//System.out.println("request_token->"+request_token);
	HttpHeaders headers2 = new HttpHeaders();
	headers2.add("Authorization","Bearer "+auth_token);
	HttpEntity<String> request2 = new HttpEntity<String>(headers2);
	MultiValueMap< String,String> params =  new LinkedMultiValueMap<>();
	params.add("dept_id","SE00021");
	try {
		params.add("request_str", URLEncoder.encode(en.getRequest_str(), "UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	params.add("request_token", en.getRequest_token());
	params.add("version", "v1.0");
	UriComponentsBuilder   builder = UriComponentsBuilder.fromUriString("https://egw.bharatapi.gov.in/t/ecourts.gov.in/CNR_Search/v1.0/cnrFullCaseDetails").queryParams(params).encode();
	URI uri = builder.build(true).toUri();
	ResponseEntity<String> response2 = r2.exchange(uri, HttpMethod.GET,request2,String.class);
	//System.out.println("success1first");
	Object o= (Object) response2.getBody();
    String S= response2.getBody();
   	JSONObject jsnobject = new JSONObject(S);
	String dild = jsnobject.get("response_str").toString();
  
    String result = dcrpt.decrypt(dild.getBytes());
    return result;
//	return "success";
}
	
	
	
}
