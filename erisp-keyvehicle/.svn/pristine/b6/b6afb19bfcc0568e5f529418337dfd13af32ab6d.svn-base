package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.VehicleOnlineState;
import com.gkhb.keyvehicle.model.param.QueryConditionData;


/**
 *	车辆在线状态
 *	@author eddy
 */
@Repository
public interface VehicleOnlineStateMapper {
	
	/**
	 * 更新在线车辆
	 * @param vehicleOnlineState
	 */
	public void updateVehicleOnline(VehicleOnlineState vehicleOnlineState);
	/**
	 * 从实时表和历史表查在线车辆
	 * @return
	 */
	public List<VehicleOnlineState> queryOnlineVehicles(@Param("queryConditionData")QueryConditionData queryConditionData);
	/**
	 * 查询所有在线离线
	 * @return
	 */
	public List<VehicleOnlineState> queryAllOnline();
	

}
