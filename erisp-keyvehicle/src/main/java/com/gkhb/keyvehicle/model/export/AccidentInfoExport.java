package com.gkhb.keyvehicle.model.export;


/*
 * 事故信息导出
 * 
 */
public class AccidentInfoExport {
	/**
	 * 车牌号码
	 */
	private String plateNumber;
	/**
	 * 所属企业
	 */
	private String ascriptionCompany;
	/**
	 * 企业联系人
	 */
	private String contacts;
	/**
	 * 联系电话
	 */
	private String phoneNumber;
	/**
	 * 主管部门
	 */
	private String competentAuthority;
	/**
	 * 事故类型
	 */
	private String accidentType;
	/**
	 * 事故发生时间
	 */
	private String accidentTime;
	/**
	 * 事故地点
	 */
	private String accidentPlace;
	/**
	 * 事故责任
	 */
	private String accidentLiability;
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getAscriptionCompany() {
		return ascriptionCompany;
	}
	public void setAscriptionCompany(String ascriptionCompany) {
		this.ascriptionCompany = ascriptionCompany;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompetentAuthority() {
		return competentAuthority;
	}
	public void setCompetentAuthority(String competentAuthority) {
		this.competentAuthority = competentAuthority;
	}
	public String getAccidentType() {
		return accidentType;
	}
	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}
	public String getAccidentTime() {
		return accidentTime;
	}
	public void setAccidentTime(String accidentTime) {
		this.accidentTime = accidentTime;
	}
	public String getAccidentPlace() {
		return accidentPlace;
	}
	public void setAccidentPlace(String accidentPlace) {
		this.accidentPlace = accidentPlace;
	}
	public String getAccidentLiability() {
		return accidentLiability;
	}
	public void setAccidentLiability(String accidentLiability) {
		this.accidentLiability = accidentLiability;
	}

}
