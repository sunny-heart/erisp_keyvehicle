package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.TravelTime;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.WarningSetView;


/**
 *	车辆行驶时间mapper
 *	@author weidongping
 *	@createTime 2017年9月7日 上午10:27:02
 */
@Repository
public interface TravelTimeMapper {

	/**
	 * 添加行驶时间
	 * @param  traveltime
	 */
	public void addTravelTime(TravelTime traveltime);

	
	/**
	 * 查询行驶时间
	 * @return
	 */
	public List<TravelTime> queryTravelTime();

	/**
	 * 修改行驶时间
	 * @param  traveltime
	 * @return
	 */
	public int updateTravelRoute(TravelTime traveltime);


    /**
     * 根据车辆id查询行驶时间
     * @param vehicleId
     * @return
     */
    public TravelTime queryTravelTimeByVehicleId(@Param("vehicleId")String vehicleId);


    /**
     * 根据车辆类型查询行驶时间
     * @param vehicleType
     * @return
     */
    public TravelTime queryTravelTimeByVehicleType(@Param("vehicleType")String vehicleType);
	
    /**
     * 查询预警信息-车辆行驶时间
     * @return
     */
    public List<WarningSetView> queryWarningInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
    
    /**
     * 根据id删除预警信息
     * @param id
     * @return
     */
    public int deleteWarningInfo(String id);


    /**
     * 根据id查询行驶时间
     * 
     * @param id
     * @return
     */
    public TravelTime queryTravelTimeById(String id);
}
