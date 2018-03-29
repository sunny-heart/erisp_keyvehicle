package com.gkhb.keyvehicle.service;

import java.util.List;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.SignView;
import com.gkhb.keyvehicle.model.view.WarningSign;

/**
   * 预警签收
   * @ClassName: WarningSign
   * 
   */
public interface WarningSignService{
	
	/**
	 * 搜索所有预警信息
	 * @return
	 */
	public List<WarningSign> queryAllWarningSign();
	
	/**
	 * 根据条件分页搜索预警信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	public List<WarningSign> searchWarningInfo(QueryConditionData queryConditionData,Page page);
	
	/**
	 * 修改签收状态
	 * @param warningSign
	 * @return
	 */
	public int updateSignState(SignView signView);
}
