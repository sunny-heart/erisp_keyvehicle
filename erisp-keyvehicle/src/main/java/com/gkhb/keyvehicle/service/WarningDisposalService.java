package com.gkhb.keyvehicle.service;

import java.util.List;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.DisposalEntry;
import com.gkhb.keyvehicle.model.DisposalProcess;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.WarningDisposalView;

/**
 * 预警处置服务接口类
 * 
 * @author 张顺江
 * @createTime 2017年9月10日 下午11:43:29
 */
public interface WarningDisposalService {

    /**
     * 根据用户类型和部门名称获取预警处置信息
     * 
     * @param userType 1：交管局部门 2：行业主管部门
     * @param department 部门名称
     * @param currentPage 当前页
     * @param pageSize 当前页显示记录条数
     * @return
     */
    public List<WarningDisposalView> queryDisposalProcess(QueryConditionData queryConditionData,Page page);
    
    /**
     * 查询已处置的预警信息
     * @param page
     * @return
     */
    public List<WarningDisposalView> queryDisposaledInfo(QueryConditionData queryConditionData,Page page);

    /**
     * 更新预警处置流程
     * 
     * @param warningDisposalView
     * @return boolean
     */
    public boolean updateDisposalProcess(DisposalProcess disposalProcess);
    
    /**
	 * 更新处置录入流程
	 * @param disposalEntry
	 * @return
	 */
	public boolean updateDisposalEntry(DisposalEntry disposalEntry);
    
    /**
     * 根据预警ID查询一条预警处置信息，预警信息，车辆信息
     * @param warningId
     * @return
     */
    public WarningDisposalView queryOneWarningDisposalInfo(String warningId);

}
