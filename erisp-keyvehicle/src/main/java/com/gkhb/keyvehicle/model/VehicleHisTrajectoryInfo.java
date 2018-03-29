package com.gkhb.keyvehicle.model;

import java.util.Date;

/**
 *	车辆历史轨迹实体类
 *	@author Colin
 *	@createTime 2017年7月4日 上午10:27:02
 */
public class VehicleHisTrajectoryInfo {
	
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 车牌号码
	 */
	private String plateNumber;
	/**
	 * 纬度
	 */
	private double latitude;
	/**
	 * 经度
	 */
	private double longitude;
	/**
	 * 车速
	 */
	private double speed;
	/**
	 * GPS终端设备上报时间
	 */
	private Date reportTime;
	
	
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "VehicleHisTrajectoryInfo [plateNumber=" + plateNumber + ", latitude=" + latitude + ", longitude="
				+ longitude + ", speed=" + speed + ", reportTime=" + reportTime + "]";
	}
	
	

}
