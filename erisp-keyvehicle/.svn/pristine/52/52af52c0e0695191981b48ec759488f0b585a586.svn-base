package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.export.AccidentInfoExport;
import com.gkhb.keyvehicle.model.export.EarlyWarningInfoExport;
import com.gkhb.keyvehicle.model.export.IllegalInfoExport;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.EarlyWarningInfoView;

/**
 * 预警信息表mapper
 * 
 * @author weidongping
 * @createTime 2017年9月7日 上午10:27:02
 */
@Repository
public interface EarlyWarningInfoMapper {
    /**
     * 添加预警信息
     * 
     * @param disposalprocess
     */
    public void addEarlyWarningInfo(EarlyWarningInfo earlyWarningInfo);

    /**
     * 查询所有预警信息
     * 
     * @return
     */
    public List<EarlyWarningInfo> queryEarlyWarningInfo();

    /**
     * 修改预警信息
     * 
     * @param earlywarninginfo }
     * @return
     */
    public int updateEarlyWarningInfo(EarlyWarningInfo earlyWarningInfo);

    /**
     * 根据车辆id、预警类型查询预警信息
     * 
     * @param id
     * @param warningType
     * @return
     */
    public EarlyWarningInfo queryEarlyWarningInfoByVehicleIdAndWarningType(@Param("vehicleId") String vehicleId, @Param("warningType") String warningType);

    /**
     * 根据预警id查询预警信息
     * 
     * @param warningId
     * @return EarlyWarningInfo
     */
    public EarlyWarningInfo queryEarlyWarningInfoById(@Param("id") String id);
    
    /**
     * 根据预警id查询预警信息
     * 
     * @param warningId
     * @return EarlyWarningInfo
     */
    public EarlyWarningInfo queryEarlyWarningInfoByPlateNumber(@Param("queryConditionData")QueryConditionData queryConditionData);
    
    /**
     * 根据预警id查询已处置的预警信息
     * 
     * @param warningId
     * @return EarlyWarningInfo
     */
    public EarlyWarningInfo queryDisposalInfoById(@Param("queryConditionData")QueryConditionData queryConditionData);
    
    /**
     * 查车辆数及预警数
     * @return
     */
    public EarlyWarningInfoView queryEarlyWarningInfoCounts(@Param("queryConditionData")QueryConditionData queryConditionData);
    
    /**
     * 根据车辆id查询最新一条预警信息
     * 
     * @param id
     * @return
     */
    public EarlyWarningInfo queryEarlyWarningInfoByVehicleId(@Param("vehicleId") String vehicleId);
    
    /**
	 * 预警查询
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoView> queryEarlyWaringInfoList(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
	
	/**
	 * 预警查询(不分页)
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoView> queryEarlyWaringInfoListCounts(@Param("queryConditionData")QueryConditionData queryConditionData);
	
	/**
	 * 预警信息导出
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoExport> exportEarlyWarningInfo(@Param("queryConditionData")QueryConditionData queryConditionData);
	/**
	 * 事故信息导出
	 * @param queryConditionData
	 * @return
	 */
	public List<AccidentInfoExport> exportAccidentInfo(@Param("queryConditionData")QueryConditionData queryConditionData);
	/**
	 * 违法信息导出
	 * @param queryConditionData
	 * @return
	 */
	public List<IllegalInfoExport> exportIllegalInfo(@Param("queryConditionData")QueryConditionData queryConditionData);
	/**
	 * 事故信息
	 * @param queryConditionData
	 * @return
	 */
	public List<AccidentInfoExport> queryAccidentInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
	/**
	 * 违法信息
	 * @param queryConditionData
	 * @return
	 */
	public List<IllegalInfoExport> queryIllegalInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
	
	/**
	 * 预警查询页面查询预警信息
	 * @param queryConditionData
	 * @return
	 */
	public List<EarlyWarningInfoView> queryEarlyWaring(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
}
