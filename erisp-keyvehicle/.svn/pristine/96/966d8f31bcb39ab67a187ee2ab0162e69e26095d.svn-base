package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.AccidentInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	事故信息Mapper
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午8:50:14
 */
@Repository
public interface AccidentInfoMapper {
	
	/**
	 * 查询所有事故信息
	 * @return
	 */
	List<AccidentInfo> queryAccident();
	
	/**
	 * 根据id查询事故信息
	 * @param id
	 * @return
	 */
	AccidentInfo queryAccidentInfoById(String id);
	
	/**
	 * 根据车牌、事故时间、事故类型分页查询事故信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	List<AccidentInfo> searchAccidentInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);

	/**
	 * 查询事故信息--不分页
	 * @param queryConditionData
	 * @return
	 */
	List<AccidentInfo> searchAccidentInfo(@Param("queryConditionData")QueryConditionData queryConditionData);
}
