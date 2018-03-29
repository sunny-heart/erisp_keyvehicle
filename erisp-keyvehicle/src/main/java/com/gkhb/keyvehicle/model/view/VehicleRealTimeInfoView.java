package com.gkhb.keyvehicle.model.view;

import java.util.Date;

/**
 *	车辆实时信息返回类
 *	@author Colin
 *	@createTime 2017年7月3日 下午3:43:38
 */
/**
 * @author Administrator
 *
 */
public class VehicleRealTimeInfoView {

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
	 * GPS终端设备上报时间
	 */
	private Date reportTime;

	/**
	 * 车辆速度
	 */
	private double speed;
	/**
	 * 最大车辆速度
	 */
	private double maxSpeed;
	/**
	 * 车辆类型
	 */
	private String vehicleType;
	/**
	 * 车辆类别
	 */
	private String plateType;
	/**
	 * 车辆图片
	 */
	private String platePictureUrl;
	/**
	 * 角度
	 */
	private double course;
	/**
	 * 行驶时间--分钟
	 */
	private int driveTime; 
	/**
	 * 预警次数
	 */
	private int warningTimes;
	/**
	 * 当前年度预警次数
	 */
	private int yearWarningTimes;
	/**
	 * 连续行驶时间
	 */
	private long continueDriveTime;
	/**
	 * 车架号
	 */
	private String vehicleFrameNumber;
	/**
	 * 所属企业
	 */
	private String ascriptionCompany;
	/**
	 * 主管部门
	 */
	private String competentAuthority;
	/**
	 * 注册登记日期
	 */
	private Date registrationDate;
	/**
	 * 预警类型
	 */
	private String warningType;
	/**
	 * 预警结束时间
	 */
	private Date warningEndTime;
	/**
	 * 车辆年审状态
	 */
	private String motTestState;
	/**
	 * 事故次数
	 */
	private int accidentTotal;
	/**
	 * 违法次数
	 */
	private int illegalTotal;
	/**
	 * 入城证
	 */
	private String intoCityCard;
	/**
	 * 运输证
	 */
	private String transportCard;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

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

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getCourse() {
		return course;
	}

	public void setCourse(double course) {
		this.course = course;
	}

	public int getDriveTime() {
		return driveTime;
	}

	public void setDriveTime(int driveTime) {
		this.driveTime = driveTime;
	}

	public int getWarningTimes() {
		return warningTimes;
	}

	public void setWarningTimes(int warningTimes) {
		this.warningTimes = warningTimes;
	}

	@Override
	public String toString() {
		return "VehicleRealTimeInfo [id=" + id + ", plateNumber=" + plateNumber
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", reportTime=" + reportTime +", vehicleType=" + vehicleType + "]";
	}

	public long getContinueDriveTime() {
		return continueDriveTime;
	}

	public void setContinueDriveTime(long continueDriveTime) {
		this.continueDriveTime = continueDriveTime;
	}

	public String getVehicleFrameNumber() {
		return vehicleFrameNumber;
	}

	public void setVehicleFrameNumber(String vehicleFrameNumber) {
		this.vehicleFrameNumber = vehicleFrameNumber;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getAscriptionCompany() {
		return ascriptionCompany;
	}

	public void setAscriptionCompany(String ascriptionCompany) {
		this.ascriptionCompany = ascriptionCompany;
	}

	public String getCompetentAuthority() {
		return competentAuthority;
	}

	public void setCompetentAuthority(String competentAuthority) {
		this.competentAuthority = competentAuthority;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public Date getWarningEndTime() {
		return warningEndTime;
	}

	public void setWarningEndTime(Date warningEndTime) {
		this.warningEndTime = warningEndTime;
	}

	public String getMotTestState() {
		return motTestState;
	}

	public void setMotTestState(String motTestState) {
		this.motTestState = motTestState;
	}

	public int getAccidentTotal() {
		return accidentTotal;
	}

	public void setAccidentTotal(int accidentTotal) {
		this.accidentTotal = accidentTotal;
	}

	public int getIllegalTotal() {
		return illegalTotal;
	}

	public void setIllegalTotal(int illegalTotal) {
		this.illegalTotal = illegalTotal;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getIntoCityCard() {
		return intoCityCard;
	}

	public void setIntoCityCard(String intoCityCard) {
		this.intoCityCard = intoCityCard;
	}

	public String getTransportCard() {
		return transportCard;
	}

	public void setTransportCard(String transportCard) {
		this.transportCard = transportCard;
	}

	public int getYearWarningTimes() {
		return yearWarningTimes;
	}

	public void setYearWarningTimes(int yearWarningTimes) {
		this.yearWarningTimes = yearWarningTimes;
	}

	public String getPlatePictureUrl() {
		return platePictureUrl;
	}

	public void setPlatePictureUrl(String platePictureUrl) {
		this.platePictureUrl = platePictureUrl;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	

}
