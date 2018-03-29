package com.gkhb.keyvehicle.service;

import java.util.List;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.WarningSetView;

/**
 * 预警设置业务接口
 * @author WeiGuo
 * @data 2017年9月23日下午5:12:45
 */
public interface WarningSetService {

	/**
	 * 添加预警
	 */
	public int addWarning(WarningSetView warningData);
	
	List<WarningSetView> queryWarningInfoList(QueryConditionData queryConditionData,Page page);
	
	int deleteWarningInfo(String id,String warningType);
	
	/**
	 * 修改预警信息
	 * @param warningData
	 * @return
	 */
	public int updateWarning(WarningSetView warningData);
}
