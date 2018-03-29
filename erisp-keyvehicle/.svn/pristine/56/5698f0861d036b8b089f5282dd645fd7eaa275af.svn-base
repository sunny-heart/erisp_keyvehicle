package com.gkhb.keyvehicle.service;


import com.gkhb.keyvehicle.model.RealTimeData;
import com.gkhb.keyvehicle.model.TravelTime;
import com.gkhb.keyvehicle.model.WarningRule;

import java.util.List;

public interface WarnRuleService {
	public TravelTime TimeRuleWarn(RealTimeData data);

	/**
	 * 新增预警规则
	 *
	 * @param warningRule
	 * @return
	 */
    int add(WarningRule warningRule);

	/**
	 * 查询预警信息
	 * @param warningRule
	 * @return
	 */
	List<WarningRule> queryWarningRule(WarningRule warningRule);

	/**
	 * 更新预警信息
	 * @param warningRule
	 * @return
	 */
	int update(WarningRule warningRule);

	/**
	 * 删除预警信息
	 * @param warningRule
	 * @return
	 */
    int delete(WarningRule warningRule);
}
