package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.ReportsView;

/**
 *	统计报表
 */
@Repository
public interface ReportsMapper {
	
	/**
	 * 根据车辆类型统计
	 * @return
	 */
	public List<ReportsView> countByTypes(@Param("queryConditionData")QueryConditionData queryConditionData);
	
	/**
	 * 根据主管部门统计
	 * @return
	 */
	public List<ReportsView> countByDepartments(@Param("queryConditionData")QueryConditionData queryConditionData);
	
	/**
	 * 根据分局统计预警数量
	 * @param queryConditionData
	 * @return
	 */
	public List<ReportsView> countAllWarningBySuboffices(@Param("queryConditionData")QueryConditionData queryConditionData);
	
	/**
	 * 根据公司统计预警数量
	 * @param queryConditionData
	 * @return
	 */
	public List<ReportsView> countAllWarningByCompany(@Param("queryConditionData")QueryConditionData queryConditionData);
	
}
