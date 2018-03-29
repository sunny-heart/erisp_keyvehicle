package com.gkhb.keyvehicle.service;

import java.util.List;

import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.VehicleRealTimeInfoView;

/**
 *	车辆实时信息服务接口
 *	@author Colin
 *	@createTime 2017年7月4日 上午9:46:13
 */
public interface VehicleRealTimeInfoService {
	
	/**
	 * 查询车辆实时信息
	 * @return
	 */
	public List<VehicleRealTimeInfoView> queryVehicleRealTimeInfo();
	
	/**
	 * 查询某一辆车的实时信息
	 * @param plateNumber
	 * @return
	 */
	public VehicleRealTimeInfoView queryOneVehicleRealTimeInfo(String plateNumber);
	
	/**
	 * 查询车辆实时速度
	 * @return
	 */
	public double queryVehicleRealTimeInfoSpeed();
	/**
	 * 查车辆行驶时间和速度等
	 * @param plateNumber
	 * @return
	 */
	public VehicleRealTimeInfoView queryVehicleDriveInfo(QueryConditionData queryConditionData);
	/**
	 * 统计某一车辆预警
	 * @return
	 */
	public VehicleRealTimeInfoView queryOneWarningTotal(QueryConditionData queryConditionData);
	
	/**
	 * 统计某一车辆当前年度预警次数
	 * @param plateNumber
	 * @return
	 */
	public VehicleRealTimeInfoView queryCurrentYearWarningTotal(QueryConditionData queryConditionData);
	
}
