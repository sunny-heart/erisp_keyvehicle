package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.VehicleHisTrajectoryInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	车辆历史轨迹Mapper
 *	@author Colin
 *	@createTime 2017年7月4日 上午10:30:06
 */
@Repository
public interface VehicleHisTrajectoryInfoMapper {
	
	/**
	 * 根据车牌号码查询车辆历史轨迹
	 * @param id
	 * @return
	 */
	public List<VehicleHisTrajectoryInfo> searchVehicleHisTrajectoryInfo(@Param("queryConditionData")QueryConditionData queryConditionData,@Param("plateNumber")String plateNumber);
	
	/**
	 * 查询某段时间内某辆车的位置信息
	 */
	List<VehicleHisTrajectoryInfo> queryLocations(@Param("queryConditionData")QueryConditionData queryConditionData);

}
