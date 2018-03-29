package com.gkhb.keyvehicle.service;

import java.util.List;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.AccidentInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	事故信息服务接口
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午9:34:16
 */
public interface AccidentInfoService {
	
	/**
	 * 查询所有事故信息
	 * @return
	 */
	public List<AccidentInfo> queryAccident();
	
	/**
	 * 根据id查询事故信息
	 * @param id
	 * @return
	 */
	public AccidentInfo queryAccidentInfoById(String id);
	
	/**
	 * 根据车牌、事故时间、事故类型分页查询事故信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	public List<AccidentInfo> searchAccidentInfo(QueryConditionData queryConditionData,Page page);
	
	/**
	 * 查询事故信息--不分页
	 * @param queryConditionData
	 * @return
	 */
	public List<AccidentInfo> searchAccidentInfoNoPage(QueryConditionData queryConditionData);

}
