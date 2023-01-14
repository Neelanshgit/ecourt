package com.consume.bhulekh.entity.Dao;


public class PartyDao {


	private String Cino;
	private String item_no;
	private String khata_number;
	private String area;
	private String survey_no;
	private String party_name;
	private String taluka_code;
	private String village_code;
	public PartyDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PartyDao(String cino, String item_no, String khata_number, String area, String survey_no, String party_name,
			String taluka_code, String village_code) {
		super();
		Cino = cino;
		this.item_no = item_no;
		this.khata_number = khata_number;
		this.area = area;
		this.survey_no = survey_no;
		this.party_name = party_name;
		this.taluka_code = taluka_code;
		this.village_code = village_code;
	}
	public String getCino() {
		return Cino;
	}
	public void setCino(String cino) {
		Cino = cino;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getKhata_number() {
		return khata_number;
	}
	public void setKhata_number(String khata_number) {
		this.khata_number = khata_number;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSurvey_no() {
		return survey_no;
	}
	public void setSurvey_no(String survey_no) {
		this.survey_no = survey_no;
	}
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	public String getTaluka_code() {
		return taluka_code;
	}
	public void setTaluka_code(String taluka_code) {
		this.taluka_code = taluka_code;
	}
	public String getVillage_code() {
		return village_code;
	}
	public void setVillage_code(String village_code) {
		this.village_code = village_code;
	}
	@Override
	public String toString() {
		return "PartyDao [Cino=" + Cino + ", item_no=" + item_no + ", khata_number=" + khata_number + ", area=" + area
				+ ", survey_no=" + survey_no + ", party_name=" + party_name + ", taluka_code=" + taluka_code
				+ ", village_code=" + village_code + "]";
	}
	
	
	
	
			
}
