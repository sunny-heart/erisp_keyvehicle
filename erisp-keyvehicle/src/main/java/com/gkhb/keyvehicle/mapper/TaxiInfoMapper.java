package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.TaxiInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.TaxiInfoView;


/**
 * 出租车信息Mapper
 * @author Administrator
 *
 */
@Repository
public interface TaxiInfoMapper {
	
	/**
	 * 分页查询出租车信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	List<TaxiInfoView> queryTaxiInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
	
	/**
     * 根据车牌号码查询出租车信息
     * @param plateNumber
     * @return
     */
    TaxiInfo queryTaxiInfoByPlateNumber(@Param("queryConditionData")QueryConditionData queryConditionData);
    
    /**
	 * 根据车牌查询所有车辆
	 * @param plateNumber
	 * @return
	 */
	List<TaxiInfoView> searchTaxiInfoByPlateNumber(@Param("plateNumber")String plateNumber);
	
	/**
	 * 查询所有出租车信息
	 * @return
	 */
	List<TaxiInfo> queryAllTaxiInfo();
}
