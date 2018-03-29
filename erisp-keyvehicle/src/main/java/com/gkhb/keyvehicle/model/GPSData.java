package com.gkhb.keyvehicle.model;

import java.util.Date;


/**
 * 历史GPS位置实体类
 * @author 张顺江
 * @createTime 2017年9月7日 上午10:15:42
 */
public class GPSData {
	
    /**
     * 主键ID
     */
    private String id;
    /**
     * 外围系统编号
     */
    private String systemNumber;
    /**
     * 车牌号码
     */
    private String plateNumber;
    /**
     * 报警标志
     */
    private String warnFlag;
    /**
     * 状态
     */
    private String state;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 海拔
     */
    private double altitude;
    /**
     * 车速
     */
    private double speed;
    /**
     * 角度
     */
    private double course;
    /**
     * GPS终端设备上报时间
     */
    private Date reportTime;
    /**
     * 数据接收时间
     */
    private Date receiveTime;
    /**
     * 清洗状态
     */
    private double etlState;
    /**
     * 车牌类型
     */
    private String vehicleType;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSystemNumber() {
        return systemNumber;
    }
    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public String getWarnFlag() {
        return warnFlag;
    }
    public void setWarnFlag(String warnFlag) {
        this.warnFlag = warnFlag;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
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
    public double getAltitude() {
        return altitude;
    }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getCourse() {
        return course;
    }
    public void setCourse(double course) {
        this.course = course;
    }
    public Date getReportTime() {
        return reportTime;
    }
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
    public Date getReceiveTime() {
        return receiveTime;
    }
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
	public double getEtlState() {
		return etlState;
	}
	public void setEtlState(double etlState) {
		this.etlState = etlState;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	
}
