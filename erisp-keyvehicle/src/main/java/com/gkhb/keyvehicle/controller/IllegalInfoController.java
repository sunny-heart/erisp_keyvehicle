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
import com.gkhb.keyvehicle.model.IllegalInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.service.IllegalInfoService;

/**
 *	违法信息控制器
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午11:03:55
 */
@Controller
@RequestMapping("/illegalInfo")
public class IllegalInfoController extends BaseController{
	
	@Autowired
	private IllegalInfoService illegalInfoService;
	
	/**
     * 查询所有违法信息
     * @return
     */
    @RequestMapping(value = "/query" , method = RequestMethod.GET)
    public Model query(){
        Model model = new ExtendedModelMap();
        List<IllegalInfo> illegalInfoList = illegalInfoService.queryIllegal();
        model.addAttribute("illegalInfoList", illegalInfoList);
        return model;
    }
    
    /**
     * 根据id查询违法信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryIllegalInfoById", method = RequestMethod.GET)
    public Model queryIllegalInfoById(String id) {
    	Model model = new ExtendedModelMap();
    	IllegalInfo illegalInfo = illegalInfoService.queryIllegalInfoById(id);
	    model.addAttribute("illegalInfo", illegalInfo);
	    return model;
    }
    
    /**
     * 分页查询违法信息
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/searchIllegalInfo" , method = RequestMethod.GET)
    public Model searchIllegalInfo(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        Page page = new Page(queryConditionData);
        List<IllegalInfo> illegalInfoList = 
        		illegalInfoService.searchIllegalInfo(queryConditionData, page);
        model.addAttribute("illegalInfoList", illegalInfoList);
        model.addAttribute("page", page);
        return model;
    }

    /**
     * 查询违法信息--不分页
     * @param queryConditionData
     * @return
     */
    @RequestMapping(value = "/searchIllegalInfoNoPage" , method = RequestMethod.GET)
    public Model searchIllegalInfoNoPage(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        List<IllegalInfo> illegalInfoList = 
        		illegalInfoService.searchIllegalInfoNoPage(queryConditionData);
        model.addAttribute("illegalInfoList", illegalInfoList);
        return model;
    }
}
