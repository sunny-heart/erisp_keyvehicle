package com.gkhb.keyvehicle.model;

import java.util.Date;

/**
 *	新预警信息实体类(包括处置相关信息)
 *	@author chenxiaojie
 *	@createTime 2017年11月9日 下午12:42:37
 */
public class DsWarningInfo {
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 车辆号码
	 */
	private String plateNumber;
	/**
	 * 预警类型
	 */
	private String warningType;
	/**
	 * 预警开始时间
	 */
	private Date warningStartTime;
	/**
	 * 预警结束时间
	 */
	private Date warningEndTime;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 行驶速度
	 */
	private double speed; 
	 /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;
    /**
	 * 处置方式，0未处置，1已处置，2忽略-错误，3忽略-重复预警，4-录入处置，5-录入忽略
	 */
	private int disposalWay;
	/**
	 * 车辆类型
	 */
	private String vehicleType;
	/**
	 * 所属辖区名称
	 */
	private String mapArea;
	/**
	 * 街道名称
	 */
	private String roadName;
	/**
	 * 签收状态，0未签收 1已签收
	 */
	private int signState;
	/**
	 * 处理人员ID值
	 */
	private String disposalUserId;
	/**
	 * 处理时间
	 */
	private Date disposalTime;
	/**
	 * 处理细则
	 */
	private String disposalDetail;
	/**
	 * 录入细则
	 */
	private String entryDetail;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public Date getWarningStartTime() {
		return warningStartTime;
	}
	public void setWarningStartTime(Date warningStartTime) {
		this.warningStartTime = warningStartTime;
	}
	public Date getWarningEndTime() {
		return warningEndTime;
	}
	public void setWarningEndTime(Date warningEndTime) {
		this.warningEndTime = warningEndTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getDisposalWay() {
		return disposalWay;
	}
	public void setDisposalWay(int disposalWay) {
		this.disposalWay = disposalWay;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getMapArea() {
		return mapArea;
	}
	public void setMapArea(String mapArea) {
		this.mapArea = mapArea;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public int getSignState() {
		return signState;
	}
	public void setSignState(int signState) {
		this.signState = signState;
	}
	public String getDisposalUserId() {
		return disposalUserId;
	}
	public void setDisposalUserId(String disposalUserId) {
		this.disposalUserId = disposalUserId;
	}
	public Date getDisposalTime() {
		return disposalTime;
	}
	public void setDisposalTime(Date disposalTime) {
		this.disposalTime = disposalTime;
	}
	public String getDisposalDetail() {
		return disposalDetail;
	}
	public void setDisposalDetail(String disposalDetail) {
		this.disposalDetail = disposalDetail;
	}
	public String getEntryDetail() {
		return entryDetail;
	}
	public void setEntryDetail(String entryDetail) {
		this.entryDetail = entryDetail;
	}
	

}
