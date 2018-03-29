package com.gkhb.keyvehicle.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.common.utils.ExportExcel;
import com.gkhb.keyvehicle.model.export.AccidentInfoExport;
import com.gkhb.keyvehicle.model.export.EarlyWarningInfoExport;
import com.gkhb.keyvehicle.model.export.IllegalInfoExport;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.EarlyWarningInfoView;
import com.gkhb.keyvehicle.service.EarlyWarningInfoService;

/**
 * 预警信息控制器类
 * 
 */
@Controller
@RequestMapping(("/earlyWarningInfo"))
public class EarlyWarningInfoController {

    @Autowired
    EarlyWarningInfoService earlyWarningInfoService;

    /**
     * 查车辆数及预警数
     * 
     * @return Model 返回类型
     */
    @RequestMapping(value = "/queryEarlyWarningInfoCounts", method = RequestMethod.GET)
    public Model queryEarlyWarningInfoCounts(QueryConditionData queryConditionData) {
        Model model = new ExtendedModelMap();
        if(queryConditionData.getNewChecked()!=null || queryConditionData.getDangerousChecked()!=null
        		|| queryConditionData.getColdChecked()!=null || queryConditionData.getOrdinaryChecked()!=null){
        	queryConditionData.setVehicleType("typeChecked");
        }else{
        	queryConditionData.setVehicleType(null);
        }
        Date nowDate = new Date();
        queryConditionData.setCurrentDate(nowDate);
        EarlyWarningInfoView earlyWarningInfo = earlyWarningInfoService.queryEarlyWarningInfoCounts(queryConditionData);
        double onlineRate = 0;
        double warningRate = 0;
        if(earlyWarningInfo.getVehicleTotal()!=0){
        	onlineRate = (double)earlyWarningInfo.getCurrentOnlineTotal()/earlyWarningInfo.getVehicleTotal();
        }
        if(earlyWarningInfo.getWarningTotal()!=0){
        	warningRate = (double)earlyWarningInfo.getTodayWarningTotal()/earlyWarningInfo.getWarningTotal();
        }
        earlyWarningInfo.setCurrentOnlineRate(onlineRate*100);
        earlyWarningInfo.setTodayWarningRate(warningRate*100);
        model.addAttribute("earlyWarningInfo", earlyWarningInfo);
        return model;
    }
    
    /**
     * 预警查询
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/queryEarlyWaringInfoList", method = RequestMethod.GET)
    public Model queryEarlyWaringInfoList(QueryConditionData queryConditionData) {
        Model model = new ExtendedModelMap();
        Page page = new Page(queryConditionData);
        List<EarlyWarningInfoView> earlyWaringInfoList = earlyWarningInfoService.queryEarlyWaringInfoList(queryConditionData, page);
        model.addAttribute("earlyWaringInfoList", earlyWaringInfoList);
        model.addAttribute("total", page.getCount());
        model.addAttribute("currentPage", page.getPage());
        model.addAttribute("pages", page.getPages());
        return model;
    }
    
    /**
     * 预警查询(不分页)
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/queryEarlyWaringInfoListCounts", method = RequestMethod.GET)
    public Model queryEarlyWaringInfoListCounts(QueryConditionData queryConditionData) {
    	Model model = new ExtendedModelMap();
    	List<EarlyWarningInfoView> earlyWaringInfoListCounts = earlyWarningInfoService.queryEarlyWaringInfoListCounts(queryConditionData);
    	model.addAttribute("earlyWaringInfoListCounts", earlyWaringInfoListCounts);
    	return model;
    }

    /**
     * 导出预警信息
     * @param response
     * @param department 分局名称--全局的
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param competentAuthority 行业主管部门
     * @param ascriptionCompany 所属企业--模糊查询
     * @return
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public Model exportExcel(HttpServletResponse response, String department, String startTime,
    		String endTime, String competentAuthority, String ascriptionCompany) {
    	Model model = new ExtendedModelMap();
    	QueryConditionData queryConditionData = new QueryConditionData();
    	if(department!=null && department!=""){
    		queryConditionData.setDepartment(department);
    	}
    	if(startTime!=null && startTime!=""){
    		queryConditionData.setStartTime(startTime);
    	}
    	if(endTime!=null && endTime!=""){
    		queryConditionData.setEndTime(endTime);
    	}
    	if(competentAuthority!=null && competentAuthority!=""){
    		queryConditionData.setCompetentAuthority(competentAuthority);
    	}
    	if(ascriptionCompany!=null && ascriptionCompany!=""){
    		queryConditionData.setAscriptionCompany(ascriptionCompany);
    	}
    	//预警
    	List<EarlyWarningInfoExport> earlyWaringInfoList = earlyWarningInfoService.exportEarlyWarningInfo(queryConditionData);	
    	ArrayList<Object> warningList = new ArrayList<Object>();
    	for(EarlyWarningInfoExport e : earlyWaringInfoList){
    		warningList.add(e);
    	}
    	String[] warningTitle={"车牌号码","所属企业","企业联系人","企业联系电话","主管部门","预警类型","预警时间","预警信息","处置状态","抄报状态"};
    	
		//事故
		List<AccidentInfoExport> accidentInfoList = earlyWarningInfoService.exportAccidentInfo(queryConditionData);	
    	ArrayList<Object> accidentList = new ArrayList<Object>();
    	for(AccidentInfoExport e : accidentInfoList){
    		accidentList.add(e);
    	}
    	String[] accidentTitle={"车牌号码","所属企业","企业联系人","企业联系电话","主管部门","事故类型","事故发生时间","事故发生地点","事故责任"};
		
    	//违法
    	List<IllegalInfoExport> illegalInfoList = earlyWarningInfoService.exportIllegalInfo(queryConditionData);	
    	ArrayList<Object> illegalList = new ArrayList<Object>();
    	for(IllegalInfoExport e : illegalInfoList){
    		illegalList.add(e);
    	}
    	String[] illegalTitle={"车牌号码","所属企业","企业联系人","企业联系电话","主管部门","违法类型","违法时间","违法地点","违法行为"};
    	
		String result = ExportExcel.exportExcel(response, "预警信息.xls",warningTitle, warningList,accidentTitle,accidentList,
				illegalTitle,illegalList); 
    	model.addAttribute("exportResult", result);
    	return model;
    }
    
    /**
     * 查询事故信息列表
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/queryAccidentInfoList", method = RequestMethod.GET)
    public Model queryAccidentInfoList(QueryConditionData queryConditionData) {
    	Model model = new ExtendedModelMap();
    	Page page = new Page(queryConditionData);
    	List<AccidentInfoExport> accidentInfoList = earlyWarningInfoService.queryAccidentInfo(queryConditionData, page);
	    model.addAttribute("accidentInfoList", accidentInfoList);
	    model.addAttribute("total", page.getCount());
        model.addAttribute("currentPage", page.getPage());
        model.addAttribute("pages", page.getPages());
    	return model;
    }
    
    /**
     * 查询违法信息列表
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/queryIllegalInfoList", method = RequestMethod.GET)
    public Model queryIllegalInfoList(QueryConditionData queryConditionData) {
    	Model model = new ExtendedModelMap();
    	Page page = new Page(queryConditionData);
    	List<IllegalInfoExport> illegalInfoList = earlyWarningInfoService.queryIllegalInfo(queryConditionData, page);	
	    model.addAttribute("illegalInfoList", illegalInfoList);
	    model.addAttribute("total", page.getCount());
        model.addAttribute("currentPage", page.getPage());
        model.addAttribute("pages", page.getPages());
    	return model;
    }
    
    /**
     * 预警查询页面查询预警信息
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/queryEarlyWaring", method = RequestMethod.GET)
    public Model queryEarlyWaring(QueryConditionData queryConditionData) {
        Model model = new ExtendedModelMap();
        Page page = new Page(queryConditionData);
        List<EarlyWarningInfoView> earlyWaringInfo = earlyWarningInfoService.queryEarlyWaring(queryConditionData, page);
        model.addAttribute("earlyWaringInfo", earlyWaringInfo);
        model.addAttribute("total", page.getCount());
        model.addAttribute("currentPage", page.getPage());
        model.addAttribute("pages", page.getPages());
        return model;
    }

}
