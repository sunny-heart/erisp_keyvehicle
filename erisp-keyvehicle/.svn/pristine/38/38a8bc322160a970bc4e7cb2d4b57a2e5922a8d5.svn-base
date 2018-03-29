package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.FatigueDriving;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.WarningSetView;

/**
 * 驾驶时间mapper
 * @author WeiGuo
 * @data 2017年9月23日下午5:15:07
 */
@Repository
public interface FatigueDrivingMapper {

    /**
     * 查询预警信息-驾驶时间
     * @return
     */
    public List<WarningSetView> queryWarningInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);

    /**
     * 根据车辆id查询疲劳驾驶时间
     * @param id
     * @return
     */
    public FatigueDriving queryFatigueDrivingByVehicleId(@Param("vehicleId")String id);

    /**
     * 根据车辆类型查询疲劳驾驶时间
     * @param vehicleType
     * @return
     */
    public FatigueDriving queryFatigueDrivingByVehicleType(@Param("vehicleType")String vehicleType);
    
    /**
     * 根据id删除预警信息
     * @param id
     * @return
     */
    public int deleteWarningInfo(String id);

    /**
     * 
     * 根据id查询疲劳驾驶时间
     * 
     * @param warningSetId
     * @return
     */
    public FatigueDriving queryFatigueDrivingById(String warningSetId);
}
