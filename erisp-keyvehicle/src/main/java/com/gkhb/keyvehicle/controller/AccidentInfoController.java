package com.gkhb.keyvehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.common.spring.BaseController;
import com.gkhb.keyvehicle.model.AccidentInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.service.AccidentInfoService;

/**
 *	事故信息控制器
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午11:02:15
 */
@Controller
@RequestMapping("/accidentInfo")
public class AccidentInfoController extends BaseController{
	
	@Autowired
	private AccidentInfoService accidentInfoService;
	
	/**
     * 查询所有事故信息
     * @return
     */
    @RequestMapping(value = "/query" , method = RequestMethod.GET)
    public Model query(){
        Model model = new ExtendedModelMap();
        List<AccidentInfo> accidentInfoList = accidentInfoService.queryAccident();
        model.addAttribute("accidentInfoList", accidentInfoList);
        return model;
    }
    
    /**
     * 根据id查询事故信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryAccidentInfoById", method = RequestMethod.GET)
    public Model queryAccidentInfoById(String id) {
    	Model model = new ExtendedModelMap();
    	AccidentInfo accidentInfo = accidentInfoService.queryAccidentInfoById(id);
	    model.addAttribute("accidentInfo", accidentInfo);
	    return model;
    }
    
    /**
     * 分页查询事故信息
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/searchAccidentInfo" , method = RequestMethod.GET)
    public Model searchAccidentInfo(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        Page page = new Page(queryConditionData);
        List<AccidentInfo> accidentInfoList = 
        		accidentInfoService.searchAccidentInfo(queryConditionData, page);
        model.addAttribute("accidentInfoList", accidentInfoList);
        model.addAttribute("page", page);
        return model;
    }
    
    /**
     * 查询事故信息--不分页
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/searchAccidentInfoNoPage" , method = RequestMethod.GET)
    public Model searchAccidentInfoNoPage(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        List<AccidentInfo> accidentInfoList = 
        		accidentInfoService.searchAccidentInfoNoPage(queryConditionData);
        model.addAttribute("accidentInfoList", accidentInfoList);
        return model;
    }

}
