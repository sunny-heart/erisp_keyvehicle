package com.gkhb.keyvehicle.service;
import java.util.List;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.export.AccidentInfoExport;
import com.gkhb.keyvehicle.model.export.EarlyWarningInfoExport;
import com.gkhb.keyvehicle.model.export.IllegalInfoExport;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.view.EarlyWarningInfoView;

/**
 * 预警信息
 * @author eddy
 *
 */
public interface EarlyWarningInfoService {
	
	/**
	 * 查车辆数及预警数
	 * @param data
	 * @return
	 */
	public EarlyWarningInfoView queryEarlyWarningInfoCounts(QueryConditionData queryConditionData);
	
	/**
	 * 根据车牌号码查询预警信息
	 * @param queryConditionData
	 * @return
	 */
	public EarlyWarningInfo queryEarlyWarningInfoByPlateNumber(QueryConditionData queryConditionData);
	
	/**
	 * 根据车辆ID查询最新一条的预警信息
	 */
	public EarlyWarningInfo queryEarlyWarningInfoByVehicleId(String vehicleId);
	
	/**
	 * 预警查询
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoView> queryEarlyWaringInfoList(QueryConditionData queryConditionData,Page page);
	
	/**
	 * 预警查询(不分页)
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoView> queryEarlyWaringInfoListCounts(QueryConditionData queryConditionData);
	/**
	 * 预警信息导出
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoExport> exportEarlyWarningInfo(QueryConditionData queryConditionData);
	/**
	 * 事故信息导出
	 * @param queryConditionData
	 * @return
	 */
	public List<AccidentInfoExport> exportAccidentInfo(QueryConditionData queryConditionData);
	/**
	 * 违法信息导出
	 * @param queryConditionData
	 * @return
	 */
	public List<IllegalInfoExport> exportIllegalInfo(QueryConditionData queryConditionData);
	/**
	 * 事故信息
	 * @param queryConditionData
	 * @return
	 */
	public List<AccidentInfoExport> queryAccidentInfo(QueryConditionData queryConditionData,Page page);
	/**
	 * 违法信息
	 * @param queryConditionData
	 * @return
	 */
	public List<IllegalInfoExport> queryIllegalInfo(QueryConditionData queryConditionData,Page page);
	
	/**
	 * 预警页面查询预警信息
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoView> queryEarlyWaring(QueryConditionData queryConditionData,Page page);
}
