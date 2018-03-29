package com.gkhb.keyvehicle.model;
/**
 *	卡口基本信息实体类
 *	@author chenxiaojie
 *	@createTime 2017年10月19日 下午6:47:19
 */
public class DevCameraEvent {
	
	/**
	 * 设备编号
	 */
	private String deviceId;
	/**
	 * 路段名
	 */
	private String roadName;
	/**
	 * IP
	 */
	private String iP;
	/**
	 * 出厂编号
	 */
	private String devNo;
	/**
	 * 桩号
	 */
	private String stake;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 设备类型
	 */
	private String devType;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getiP() {
		return iP;
	}
	public void setiP(String iP) {
		this.iP = iP;
	}
	public String getDevNo() {
		return devNo;
	}
	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}
	public String getStake() {
		return stake;
	}
	public void setStake(String stake) {
		this.stake = stake;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	
	

}
