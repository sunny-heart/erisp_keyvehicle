package com.gkhb.keyvehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.mapper.EarlyWarningInfoMapper;
import com.gkhb.keyvehicle.mapper.VehicleInfoMapper;
import com.gkhb.keyvehicle.mapper.VehicleRealTimeInfoMapper;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.VehicleRealTimeInfoView;

/**
 *
 *	@author Colin
 *	@createTime 2017年7月4日 上午9:46:39
 */
@Service
public class VehicleRealTimeInfoServiceImpl implements VehicleRealTimeInfoService{

	@Autowired
	private VehicleRealTimeInfoMapper vehicleRealTimeInfoMapper;
	
	@Autowired
	private EarlyWarningInfoMapper earlyWarningInfoMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;
	
	@Override
	public List<VehicleRealTimeInfoView> queryVehicleRealTimeInfo() {
		return vehicleRealTimeInfoMapper.queryVehicleRealTimeInfo();
	}

	@Override
	public VehicleRealTimeInfoView queryOneVehicleRealTimeInfo(String plateNumber){
		VehicleRealTimeInfoView oneVehicleRealTimeInfo = vehicleRealTimeInfoMapper.queryOneVehicleRealTimeInfo(plateNumber);
			if(oneVehicleRealTimeInfo != null && !oneVehicleRealTimeInfo.equals("")){
				//预警数据从data-service获取
				/*EarlyWarningInfo earlyWarningInfo = earlyWarningInfoMapper.queryEarlyWarningInfoByVehicleId(oneVehicleRealTimeInfo.getId());
				if(earlyWarningInfo != null){
					oneVehicleRealTimeInfo.setWarningEndTime(earlyWarningInfo.getWarningEndTime());
					oneVehicleRealTimeInfo.setWarningType(earlyWarningInfo.getWarningType());
				}*/
				Integer countAccidentTotalByPlateNumber = vehicleInfoMapper.countAccidentTotalByPlateNumber(plateNumber);
				Integer countIllegalTotalByPlateNumber = vehicleInfoMapper.countIllegalTotalByPlateNumber(plateNumber);
				oneVehicleRealTimeInfo.setAccidentTotal(countAccidentTotalByPlateNumber!=null?countAccidentTotalByPlateNumber.intValue():0);
				oneVehicleRealTimeInfo.setIllegalTotal(countIllegalTotalByPlateNumber!=null?countIllegalTotalByPlateNumber.intValue():0);
			}
		return oneVehicleRealTimeInfo;
	}
	
	@Override
	public double queryVehicleRealTimeInfoSpeed() {
		// TODO Auto-generated method stub
		double realTimeSpeed=vehicleRealTimeInfoMapper.queryVehicleRealTimeInfoSpeed();
		return realTimeSpeed;
	}

	@Override
	public VehicleRealTimeInfoView queryVehicleDriveInfo(QueryConditionData queryConditionData) {
		return vehicleRealTimeInfoMapper.queryVehicleDriveInfo(queryConditionData);
	}

	@Override
	public VehicleRealTimeInfoView queryOneWarningTotal(QueryConditionData queryConditionData) {
		return vehicleRealTimeInfoMapper.queryOneWarningTotal(queryConditionData);
	}

	@Override
	public VehicleRealTimeInfoView queryCurrentYearWarningTotal(
			QueryConditionData queryConditionData) {
		return vehicleRealTimeInfoMapper.queryCurrentYearWarningTotal(queryConditionData);
	}
}
