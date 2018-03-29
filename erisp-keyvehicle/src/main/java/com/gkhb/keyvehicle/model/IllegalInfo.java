package com.gkhb.keyvehicle.model;

import java.util.Date;

/**
 *	违法信息实体类
 *	@author chenxiaojie
 *	@createTime 2017年10月2日 下午5:28:44
 */
public class IllegalInfo {

	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 号牌种类
	 */
	private String plateType;
	/**
	 * 车牌号码
	 */
	private String plateNumber;
	/**
	 * 违法时间
	 */
	private Date illegalTime;
	/**
	 * 违法地点
	 */
	private String illegalPlace;
	/**
	 * 违法行为
	 */
	private String illegalBehavior;
	/**
	 * 违法代码
	 */
	private String illegalCode;
	/**
	 * 采集机关名称
	 */
	private String collectingAuthority;
	/**
	 * 违法类型
	 */
	private String illegalType;
	/**
	 * 状态
	 */
	private int state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlateType() {
		return plateType;
	}
	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public Date getIllegalTime() {
		return illegalTime;
	}
	public void setIllegalTime(Date illegalTime) {
		this.illegalTime = illegalTime;
	}
	public String getIllegalPlace() {
		return illegalPlace;
	}
	public void setIllegalPlace(String illegalPlace) {
		this.illegalPlace = illegalPlace;
	}
	public String getIllegalBehavior() {
		return illegalBehavior;
	}
	public void setIllegalBehavior(String illegalBehavior) {
		this.illegalBehavior = illegalBehavior;
	}
	public String getIllegalCode() {
		return illegalCode;
	}
	public void setIllegalCode(String illegalCode) {
		this.illegalCode = illegalCode;
	}
	public String getCollectingAuthority() {
		return collectingAuthority;
	}
	public void setCollectingAuthority(String collectingAuthority) {
		this.collectingAuthority = collectingAuthority;
	}
	public String getIllegalType() {
		return illegalType;
	}
	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
