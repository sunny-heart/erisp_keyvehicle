package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.SignView;
import com.gkhb.keyvehicle.model.view.WarningSign;

/**
   * 预警签收Mapper
   */
@Repository
public interface WarningSignMapper {
	
	/**
	 * 查询预警签收状态
	 */
	List<WarningSign> queryAllWarningSign();
	
	/**
	 * 根据条件分页搜索预警信息
	 * @param queryConditionData
	 * @param page
	 * @return
	 */
	List<WarningSign> searchWarningInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
	
	/**
	 * 修改签收状态
	 * @param warningSign
	 * @return
	 */
	public int updateSignState(@Param("signView")SignView signView);
}
