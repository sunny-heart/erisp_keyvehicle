package com.gkhb.keyvehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.mapper.HistDataMapper;
import com.gkhb.keyvehicle.mapper.RealTimeDataMapper;
import com.gkhb.keyvehicle.mapper.VehicleHisTrajectoryInfoMapper;
import com.gkhb.keyvehicle.model.GPSData;
import com.gkhb.keyvehicle.model.VehicleHisTrajectoryInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	车辆历史轨迹信息服务接口
 *	@author Colin
 *	@createTime 2017年7月4日 上午10:45:15
 */
@Service
public class VehicleHisTrajectoryInfoServiceImpl implements VehicleHisTrajectoryInfoService{

	@Autowired
	private VehicleHisTrajectoryInfoMapper vehicleHisTrajectoryInfoMapper;
	@Autowired
	private HistDataMapper histDataMapper;
	@Autowired
	private RealTimeDataMapper realTimeDataMapper;

	@Override
	public List<VehicleHisTrajectoryInfo> searchVehicleHisTrajectoryInfo(QueryConditionData queryConditionData,String plateNumber) {
		return vehicleHisTrajectoryInfoMapper.searchVehicleHisTrajectoryInfo(queryConditionData,plateNumber);
	}

	@Override
	public List<VehicleHisTrajectoryInfo> queryLocations(QueryConditionData queryConditionData) {
		return vehicleHisTrajectoryInfoMapper.queryLocations(queryConditionData);
	}

	@Override
	public List<GPSData> queryLocationsByHistData(QueryConditionData queryConditionData) {
		return histDataMapper.queryLocationsByHistData(queryConditionData);
	}

	@Override
	public List<GPSData> queryLocationsByRealTimeData(QueryConditionData queryConditionData) {
		return realTimeDataMapper.queryLocationsByRealTimeData(queryConditionData);
	}

}
