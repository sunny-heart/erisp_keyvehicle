package com.gkhb.keyvehicle.model.export;


/*
 * 预警信息导出
 * 
 */
public class EarlyWarningInfoExport {
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
	 * 预警类型
	 */
	private String warningType;
	/**
	 * 预警时间
	 */
	private String warningStartTime;
	/**
	 * 预警信息
	 */
	private String warningInfo;
	/**
	 * 处置状态
	 */
//	private String disposalWay;
	/**
	 * 抄报状态
	 */
//	private String jgCcState;

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

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

	public String getWarningStartTime() {
		return warningStartTime;
	}

	public void setWarningStartTime(String warningStartTime) {
		this.warningStartTime = warningStartTime;
	}

	public String getWarningInfo() {
		return warningInfo;
	}

	public void setWarningInfo(String warningInfo) {
		this.warningInfo = warningInfo;
	}

}
