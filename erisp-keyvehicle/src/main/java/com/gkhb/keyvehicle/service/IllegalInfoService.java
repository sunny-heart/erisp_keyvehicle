package com.gkhb.keyvehicle.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.IllegalInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	违法信息服务接口
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午9:36:25
 */
public interface IllegalInfoService {
	
	/**
	 * 查询所有事故信息
	 * @return
	 */
	public List<IllegalInfo> queryIllegal();
	
	/**
	 * 根据id查询事故信息
	 * @param id
	 * @return
	 */
	public IllegalInfo queryIllegalInfoById(String id);
	
	/**
	 * 根据车牌、事故时间、事故类型分页查询事故信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	public List<IllegalInfo> searchIllegalInfo(QueryConditionData queryConditionData,Page page);
	
	/**
	 * 查询违法信息--不分页
	 * @param queryConditionData
	 * @return
	 */
	public List<IllegalInfo> searchIllegalInfoNoPage(QueryConditionData queryConditionData);

}
