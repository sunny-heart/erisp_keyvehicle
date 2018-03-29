package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.VehicleRealTimeInfoView;


/**
 *	车辆实时信息Mapper
 *	@author Colin
 *	@createTime 2017年7月3日 下午3:51:08
 */
@Repository
public interface VehicleRealTimeInfoMapper {
	
	/**
	 * 查询车辆实时信息
	 * @return
	 */
	public List<VehicleRealTimeInfoView> queryVehicleRealTimeInfo();
	
	/**
	 * 查询某一车的实时信息
	 * @param plateNumber 车牌号
	 * @return
	 */
	public VehicleRealTimeInfoView queryOneVehicleRealTimeInfo(@Param("plateNumber")String plateNumber);
	
	/**
	 * 查询车辆实时速度
	 * */
	public double queryVehicleRealTimeInfoSpeed();
	/**
	 * 查车辆行驶时间和速度等
	 * @param plateNumber
	 * @return
	 */
	public VehicleRealTimeInfoView queryVehicleDriveInfo(@Param("queryConditionData")QueryConditionData queryConditionData);
	/**
	 * 统计某一车辆预警
	 * @return
	 */
	public VehicleRealTimeInfoView queryOneWarningTotal(@Param("queryConditionData")QueryConditionData queryConditionData);
	/**
	 * 统计某一车辆当前年度预警次数
	 * @return
	 */
	public VehicleRealTimeInfoView queryCurrentYearWarningTotal(@Param("queryConditionData")QueryConditionData queryConditionData);
	
}
