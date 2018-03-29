package com.gkhb.keyvehicle.model;

import java.util.Date;

/**
 *	出租车驾驶员信息实体类
 */
public class TaxiDriverInfo {
	
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 出租车ID
	 */
	private String taxiId;
	/**
	 * 驾驶员编号
	 */
	private String strFwzNo;
	/**
	 * 驾驶员名字
	 */
	private String strName;
	/**
	 * 驾驶员性别
	 */
	private int strSex;
	/**
	 * 联系电话
	 */
	private String empTele;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public String getTaxiId() {
		return taxiId;
	}
	public void setTaxiId(String taxiId) {
		this.taxiId = taxiId;
	}
	public int getStrSex() {
		return strSex;
	}
	public void setStrSex(int strSex) {
		this.strSex = strSex;
	}
	public String getEmpTele() {
		return empTele;
	}
	public void setEmpTele(String empTele) {
		this.empTele = empTele;
	}
	public String getStrFwzNo() {
		return strFwzNo;
	}
	public void setStrFwzNo(String strFwzNo) {
		this.strFwzNo = strFwzNo;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
