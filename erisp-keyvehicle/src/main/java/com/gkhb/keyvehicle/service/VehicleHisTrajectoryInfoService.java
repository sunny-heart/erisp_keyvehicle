package com.gkhb.keyvehicle.service;

import java.util.List;

import com.gkhb.keyvehicle.model.GPSData;
import com.gkhb.keyvehicle.model.VehicleHisTrajectoryInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	车辆历史轨迹信息服务接口
 *	@author Colin
 *	@createTime 2017年7月4日 上午10:43:21
 */
public interface VehicleHisTrajectoryInfoService {

	/**
	 * 根据车牌号码查询车辆历史轨迹
	 * @param plateNumber
	 * @return
	 */
	public List<VehicleHisTrajectoryInfo> searchVehicleHisTrajectoryInfo(QueryConditionData queryConditionData,String plateNumber);
	
	/**
	 * 查询某段时间内某辆车的位置信息
	 */
	List<VehicleHisTrajectoryInfo> queryLocations(QueryConditionData queryConditionData);
	
	/**
	 * 通过历史数据库查询一段时间内某辆车的位置信息
	 */
	List<GPSData> queryLocationsByHistData(QueryConditionData queryConditionData);
	
	/**
	 * 通过实时数据库查询一段时间内某辆车的位置信息
	 */
	List<GPSData> queryLocationsByRealTimeData(QueryConditionData queryConditionData);
}
