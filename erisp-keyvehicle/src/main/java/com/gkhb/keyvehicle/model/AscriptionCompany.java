package com.gkhb.keyvehicle.model;

/**
 * 所属企业实体类
 * @author eddy
 *
 */
public class AscriptionCompany {
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 物流车企业名称字段
	 */
	private String ascriptionCompany;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAscriptionCompany() {
		return ascriptionCompany;
	}
	public void setAscriptionCompany(String ascriptionCompany) {
		this.ascriptionCompany = ascriptionCompany;
	}
	
}
