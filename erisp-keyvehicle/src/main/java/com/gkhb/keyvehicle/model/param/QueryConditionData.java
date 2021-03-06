package com.gkhb.keyvehicle.model.param;

import java.util.Date;

/**
 *	查询条件封装类
 *	@author Colin
 *	@createTime 2017年7月3日 上午9:24:46
 */
public class QueryConditionData {

	/**
     * 当前页
     */
    private int currentPage;
    /**
     * 当前页显示记录条数
     */
    private int pageSize;
    
    /**
	 * 主键ID
	 */
	private String id;
	/**
	 * 车辆id
	 */
	private String vehicleId;
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
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 某一时间点
     */
    private String oneTime;
    /**
     * 多少分钟之前
     */
    private String timePrevious;
    /**
	 * 纬度
	 */
	private double latitude;
	/**
	 * 经度
	 */
	private double longitude;
	/**
	 * 报警标志
	 */
	private String warnFlag;
	/**
	 * GPS终端设备上报时间
	 */
	private Date reportTime;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 部门名称
	 */
	private String department;
	/**
	 * 车辆在线状态
	 */
	private String onLineState;
	
	/**
     * 预警类型
     */
    private String warningType;
    /**
     * 系统当前时间
     */
    private Date currentDate;
    /**
     * 车辆类型之选中新能源
     */
    private String newChecked;
    /**
     * 车辆类型之选中危化品
     */
    private String dangerousChecked;
    /**
     * 车辆类型之选中冷链车
     */
    private String coldChecked;
    /**
     * 车辆类型之选中普通车
     */
    private String ordinaryChecked;
    /**
     * 年份
     * @return
     */
	private String currentYear;
	/**
	 * 当前月
	 */
	private String currentMonth;
	/**
	 * 处置状态
	 * @return
	 */
	private String warningDealState;
	/**
	 * 抄报状态
	 * @return
	 */
	private String jgCcState;
	/**
	 * 事故类型
	 */
	private String accidentType;
	/**
	 * 违法类型
	 */
	private String illegalType;
	/**
	 * 签收状态
	 */
	private String signState;
	/**
	 * 短信发送号码
	 */
	private String smsMob;
	/**
	 * 短信发送内容
	 */
	private String smsText;
	/**
	 * 页面请求类型
	 */
	private String requestType;
	
	/**
	 * url路径
	 */
	private String urlPath;
	/**
	 * 分局名称
	 */
	private String departmentName;
	/**
	 * 预警处置所用车牌
	 */
	private String plate;
	/**
	 * 预警信息表中的车辆类型
	 */
	private String warningVehicleType;
	/**
	 * 车牌号码的前两位数，如川A，川G
	 */
	private String plateNumberHead;
	/**
	 * 车牌号码除前两位数以外的后面的数字
	 */
	private String plateNumberBody;
	/**
	 * 统计时间类型
	 */
	private String countTimeType;
	/**
	 * 分局名称
	 */
	private String subofficeName;
	/**
	 * 开始年份
	 */
	private String startYear;
	/**
	 * 结束年份
	 */
	private String endYear;
	/**
	 * 开始月份
	 */
	private String startMonth;
	/**
	 * 结束月份
	 */
	private String endMonth;
	/**
	 * 开始季度
	 */
	private String startQuarter;
	/**
	 * 结束季度
	 */
	private String endQuarter;
	/**
	 * 排序
	 */
	private String sortType;
	
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
	public String getWarnFlag() {
		return warnFlag;
	}
	public void setWarnFlag(String warnFlag) {
		this.warnFlag = warnFlag;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTimePrevious() {
		return timePrevious;
	}
	public void setTimePrevious(String timePrevious) {
		this.timePrevious = timePrevious;
	}
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
	public String getOnLineState() {
		return onLineState;
	}
	public void setOnLineState(String onLineState) {
		this.onLineState = onLineState;
	}
    public String getWarningType() {
        return warningType;
    }
    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getNewChecked() {
		return newChecked;
	}
	public void setNewChecked(String newChecked) {
		this.newChecked = newChecked;
	}
	public String getDangerousChecked() {
		return dangerousChecked;
	}
	public void setDangerousChecked(String dangerousChecked) {
		this.dangerousChecked = dangerousChecked;
	}
	public String getColdChecked() {
		return coldChecked;
	}
	public void setColdChecked(String coldChecked) {
		this.coldChecked = coldChecked;
	}
	public String getOrdinaryChecked() {
		return ordinaryChecked;
	}
	public void setOrdinaryChecked(String ordinaryChecked) {
		this.ordinaryChecked = ordinaryChecked;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public String getWarningDealState() {
		return warningDealState;
	}
	public void setWarningDealState(String warningDealState) {
		this.warningDealState = warningDealState;
	}
	public String getJgCcState() {
		return jgCcState;
	}
	public void setJgCcState(String jgCcState) {
		this.jgCcState = jgCcState;
	}
	public String getAccidentType() {
		return accidentType;
	}
	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}
	public String getIllegalType() {
		return illegalType;
	}
	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}
	public String getSignState() {
		return signState;
	}
	public void setSignState(String signState) {
		this.signState = signState;
	}
	public String getSmsMob() {
		return smsMob;
	}
	public void setSmsMob(String smsMob) {
		this.smsMob = smsMob;
	}
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getWarningVehicleType() {
		return warningVehicleType;
	}
	public void setWarningVehicleType(String warningVehicleType) {
		this.warningVehicleType = warningVehicleType;
	}
	public String getPlateNumberHead() {
		return plateNumberHead;
	}
	public void setPlateNumberHead(String plateNumberHead) {
		this.plateNumberHead = plateNumberHead;
	}
	public String getPlateNumberBody() {
		return plateNumberBody;
	}
	public void setPlateNumberBody(String plateNumberBody) {
		this.plateNumberBody = plateNumberBody;
	}
	public String getCountTimeType() {
		return countTimeType;
	}
	public void setCountTimeType(String countTimeType) {
		this.countTimeType = countTimeType;
	}
	public String getSubofficeName() {
		return subofficeName;
	}
	public void setSubofficeName(String subofficeName) {
		this.subofficeName = subofficeName;
	}
	public String getOneTime() {
		return oneTime;
	}
	public void setOneTime(String oneTime) {
		this.oneTime = oneTime;
	}
	public String getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public String getStartQuarter() {
		return startQuarter;
	}
	public void setStartQuarter(String startQuarter) {
		this.startQuarter = startQuarter;
	}
	public String getEndQuarter() {
		return endQuarter;
	}
	public void setEndQuarter(String endQuarter) {
		this.endQuarter = endQuarter;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
    
}
