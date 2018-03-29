package com.gkhb.keyvehicle.model.view;


/**
 * 统计报表返回类
 */
public class ReportsView {
    
    /**
     * 车辆类型
     */
    private String vehicleType;
    
    /**
     * 主管部门
     */
    private String competentAuthority;
    
    /**
     * 分局
     */
    private String subofficeName;
    
    /**
     * 所属企业
     */
    private String companyName;
    /**
     * 时间
     */
    private String countTime;
    /**
     * 超速行驶车辆数量
     */
    private int overSpeed=0;
    /**
     * 违规世间行驶车辆数量
     */
    private int overTime=0;
    /**
     * 违规线路行驶车辆数量
     */
    private int overWay=0;
    /**
     * 疲劳驾驶车辆数量
     */
    private int fatigueDriving=0;
    /**
     * 预警统计数量
     */
    private int warningCounts=0;
    /**
     * 预警类型
     * @return
     */
    private String warningType;
    /**
     * 车辆预警数量
     */
    private int vehicleWarningCounts=0;
    /**
     * 车辆总数
     */
    private int vehicleTotals=0;

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getCompetentAuthority() {
		return competentAuthority;
	}

	public void setCompetentAuthority(String competentAuthority) {
		this.competentAuthority = competentAuthority;
	}

	public String getCountTime() {
		return countTime;
	}

	public void setCountTime(String countTime) {
		this.countTime = countTime;
	}

	public int getWarningCounts() {
		return warningCounts;
	}

	public void setWarningCounts(int warningCounts) {
		this.warningCounts = warningCounts;
	}

	public int getOverSpeed() {
		return overSpeed;
	}

	public void setOverSpeed(int overSpeed) {
		this.overSpeed = overSpeed;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public int getOverWay() {
		return overWay;
	}

	public void setOverWay(int overWay) {
		this.overWay = overWay;
	}

	public int getFatigueDriving() {
		return fatigueDriving;
	}

	public void setFatigueDriving(int fatigueDriving) {
		this.fatigueDriving = fatigueDriving;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getVehicleTotals() {
		return vehicleTotals;
	}

	public void setVehicleTotals(int vehicleTotals) {
		this.vehicleTotals = vehicleTotals;
	}

	public String getSubofficeName() {
		return subofficeName;
	}

	public void setSubofficeName(String subofficeName) {
		this.subofficeName = subofficeName;
	}
    
	public boolean compareSubofficeName(Object obj) {
        if(subofficeName.equals(((ReportsView)obj).getSubofficeName())){
            return true;
        }else{
            return false;
        }
    }

	public int getVehicleWarningCounts() {
		return vehicleWarningCounts;
	}

	public void setVehicleWarningCounts(int vehicleWarningCounts) {
		this.vehicleWarningCounts = vehicleWarningCounts;
	}
}
