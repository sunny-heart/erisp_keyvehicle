package com.gkhb.keyvehicle.service;

import java.util.List;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.TaxiInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.TaxiInfoView;


/**
 *	出租车信息服务接口
 *	@author Colin
 */
public interface TaxiInfoService {
	
	/**
	 * 分页查询出租车信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	List<TaxiInfoView> queryTaxiInfo(QueryConditionData queryConditionData,Page page);
	
	/**
	 * 根据车牌号码查询所有车辆
	 * @param plateNumber
	 * @return
	 */
	public List<TaxiInfoView> searchTaxiInfoByPlateNumber(String plateNumber);
	
	/**
	 * 根据车牌号码查询车辆信息
	 * @param plateNumber
	 * @return
	 */
	public TaxiInfo queryTaxiInfoByPlateNumber(QueryConditionData queryConditionData);
	
	
	/**
	 * 根据车牌号查询车辆的违法事故统计和预警
	 * @param plateNumber
	 * @return
	 */
	public TaxiInfoView searchVehicleInfoOfOtherInfo(String plateNumber);
	
	/**
	 * 查询所有出租车信息
	 * @return
	 */
	public List<TaxiInfo> queryAllTaxiInfo();
	
}
