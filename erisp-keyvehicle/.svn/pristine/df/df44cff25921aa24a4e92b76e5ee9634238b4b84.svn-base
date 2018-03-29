package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.IllegalInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	违法信息Mapper
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午8:52:15
 */
@Repository
public interface IllegalInfoMapper {
	
	/**
	 * 查询所有违法信息
	 * @return
	 */
	List<IllegalInfo> queryIllegal();
	
	/**
	 * 根据id查询违法信息
	 * @param id
	 * @return
	 */
	IllegalInfo queryIllegalInfoById(String id);
	
	/**
	 * 根据车牌、违法时间、违法类型分页查询违法信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	List<IllegalInfo> searchIllegalInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);

	/**
	 * 查询违法信息--不分页
	 * @param queryConditionData
	 * @return
	 */
	List<IllegalInfo> searchIllegalInfo(@Param("queryConditionData")QueryConditionData queryConditionData);
}
