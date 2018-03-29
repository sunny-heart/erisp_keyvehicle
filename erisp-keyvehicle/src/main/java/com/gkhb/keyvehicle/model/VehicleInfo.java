package com.gkhb.keyvehicle.model;

import java.util.Date;

/**
 *	车辆信息实体类
 *	@author Colin  weidongping 添加了state  plateType vehicleState competentAuthority属性
 *	@createTime 2017年6月30日 上午9:52:27
 */
public class VehicleInfo {
	
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 车牌号码
	 */
	private String plateNumber;
	/**
	 * 申报企业
	 */
	private String applyCompany;
	/**
	 * 所属企业
	 */
	private String ascriptionCompany;
	/**
	 * 车辆类型
	 */
	private String vehicleType;
	/**
	 * 核定载重
	 */
	private int authorizedLoad;
	/**
	 * 注册登记日期
	 */
	private Date registrationDate;
	/**
	 * 企业联系人
	 */
	private String contacts;
	/**
	 * 联系电话
	 */
	private String phoneNumber;
	/**
	 * 所属系统编号
	 */
	private String systemNumber;
	/**
	 * 牌照类型
	 */
	private String plateType;
	/**
	 * 车辆状态
	 */
	private String vehicleState;
	/**
	 * 主管部门
	 */
	private String competentAuthority;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 车架号
	 */
	private String vehicleFrameNumber;
	/**
	 * 车辆图片url
	 */
	private String platePictureUrl;
	/**
	 * 车辆年审日期
	 */
	private Date motTestDate;
	/**
	 * 入城证
	 */
	private String intoCityCard;
	/**
	 * 运输证
	 */
	private String transportCard;
	
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
	public String getApplyCompany() {
		return applyCompany;
	}
	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
	}
	public String getAscriptionCompany() {
		return ascriptionCompany;
	}
	public void setAscriptionCompany(String ascriptionCompany) {
		this.ascriptionCompany = ascriptionCompany;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getAuthorizedLoad() {
		return authorizedLoad;
	}
	public void setAuthorizedLoad(int authorizedLoad) {
		this.authorizedLoad = authorizedLoad;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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
	public String getSystemNumber() {
		return systemNumber;
	}
	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}
	public String getPlateType() {
		return plateType;
	}
	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}
	public String getVehicleState() {
		return vehicleState;
	}
	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}
	public String getCompetentAuthority() {
		return competentAuthority;
	}
	public void setCompetentAuthority(String competentAuthority) {
		this.competentAuthority = competentAuthority;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getVehicleFrameNumber() {
	    return vehicleFrameNumber;
	}
	public void setVehicleFrameNumber(String vehicleFrameNumber) {
	    this.vehicleFrameNumber = vehicleFrameNumber;
	}

	@Override
	public String toString() {
		return "VehicleInfo [id=" + id + ", plateNumber=" + plateNumber + ", applyCompany=" + applyCompany
				+ ", ascriptionCompany=" + ascriptionCompany + ", vehicleType=" + vehicleType + ", authorizedLoad="
				+ authorizedLoad + ", registrationDate=" + registrationDate + ", contacts=" + contacts
				+ ", phoneNumber=" + phoneNumber + ", systemNumber=" + systemNumber + ", plateType=" + plateType
				+ ", vehicleState=" + vehicleState + ", competentAuthority=" + competentAuthority + ", state=" + state
				+ ", vehiceFrameNumber=" + vehicleFrameNumber +"]";
	}
	public String getPlatePictureUrl() {
		return platePictureUrl;
	}
	public void setPlatePictureUrl(String platePictureUrl) {
		this.platePictureUrl = platePictureUrl;
	}
	public Date getMotTestDate() {
		return motTestDate;
	}
	public void setMotTestDate(Date motTestDate) {
		this.motTestDate = motTestDate;
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
	
	
}
