package com.gkhb.keyvehicle.service;
import java.util.List;

import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.ReportsView;

/**
 * 统计报表
 * @author eddy
 *
 */
public interface ReportsService {
	
	/**
	 * 根据车辆类型统计
	 * @return
	 */
	public List<ReportsView> countByTypes(QueryConditionData queryConditionData);

	/**
	 * 根据主管部门统计
	 * @return
	 */
	public List<ReportsView> countByDepartments(QueryConditionData queryConditionData);
	
	/**
	 * 根据分局统计预警数量
	 * @param queryConditionData
	 * @return
	 */
	public List<ReportsView> countAllWarningBySuboffices(QueryConditionData queryConditionData);
	
	/**
	 * 根据公司统计预警数量
	 * @param queryConditionData
	 * @return
	 */
	public List<ReportsView> countAllWarningByCompany(QueryConditionData queryConditionData);
}
