package com.gkhb.keyvehicle.model;

import java.util.Date;

/**
 *	事故信息实体类
 *	@author chenxiaojie
 *	@createTime 2017年10月2日 下午5:19:20
 */
public class AccidentInfo {
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
	 * 事故发生时间
	 */
	private Date accidentTime;
	/**
	 * 事故发生地点
	 */
	private String accidentPlace;
	/**
	 * 事故责任
	 */
	private String accidentLiability;
	/**
	 * 办案单位
	 */
	private String handlingUnits;
	/**
	 * 事故类型
	 */
	private String accidentType;
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
	public Date getAccidentTime() {
		return accidentTime;
	}
	public void setAccidentTime(Date accidentTime) {
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
	public String getHandlingUnits() {
		return handlingUnits;
	}
	public void setHandlingUnits(String handlingUnits) {
		this.handlingUnits = handlingUnits;
	}
	public String getAccidentType() {
		return accidentType;
	}
	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	

}
