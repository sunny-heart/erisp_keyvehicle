package com.gkhb.keyvehicle.model.view;

import java.util.Date;
import java.util.List;

import com.gkhb.keyvehicle.model.TaxiInfo;

/**
 *	出租车信息返回类
 *	@author chenxiaojie
 */
public class TaxiInfoView {
	
	/**
	 * 出租车ID
	 */
	private String taxiId;
	/**
	 * 车牌号码
	 */
	private String strVehicleNo;
	/**
	 * 所属企业
	 */
	private String strComshort;
	/**
	 * 转换的车牌号
	 */
	private String equipNo;
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
	 * 车辆状态
	 */
	private String vehicleState;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 车辆图片url
	 */
	private String platePictureUrl;
	/**
	 * 事故次数
	 */
	private int accidentTotal;
	/**
	 * 违法次数
	 */
	private int illegalTotal;
	/**
	 * 车辆在线状态
	 */
	private String gpsState;
	/**
	 * 预警类型
	 */
	private String warningType;
	/**
	 * 预警结束时间
	 */
	private Date warningEndTime;
	/**
	 * 预警结束时间
	 */
	private List<TaxiInfo> taxiInfoList;
	public String getTaxiId() {
		return taxiId;
	}
	public void setTaxiId(String taxiId) {
		this.taxiId = taxiId;
	}
	public String getStrVehicleNo() {
		return strVehicleNo;
	}
	public void setStrVehicleNo(String strVehicleNo) {
		this.strVehicleNo = strVehicleNo;
	}
	public String getStrComshort() {
		return strComshort;
	}
	public void setStrComshort(String strComshort) {
		this.strComshort = strComshort;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
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
	public String getVehicleState() {
		return vehicleState;
	}
	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPlatePictureUrl() {
		return platePictureUrl;
	}
	public void setPlatePictureUrl(String platePictureUrl) {
		this.platePictureUrl = platePictureUrl;
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
	public String getGpsState() {
		return gpsState;
	}
	public void setGpsState(String gpsState) {
		this.gpsState = gpsState;
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
	public List<TaxiInfo> getTaxiInfoList() {
		return taxiInfoList;
	}
	public void setTaxiInfoList(List<TaxiInfo> taxiInfoList) {
		this.taxiInfoList = taxiInfoList;
	}
	
	
	
	
}
