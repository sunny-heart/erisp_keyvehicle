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
import com.gkhb.keyvehicle.common.spring.ModelMapFactory;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.SignView;
import com.gkhb.keyvehicle.model.view.WarningSign;
import com.gkhb.keyvehicle.service.WarningSignService;

/**
   * @ClassName: WarningSign
   * 
   */
@Controller
@RequestMapping("/warningSign")
public class WarningSignController extends BaseController {
	
	@Autowired
	private WarningSignService warningSignService;
	
	/**
	 * 搜索所有預警信息(弃用)
	 * @return
	 */
	@RequestMapping(value = "/queryWarningSign" , method = RequestMethod.GET)
    public Model queryWarningSign(){
        Model model = new ExtendedModelMap();
        List<WarningSign> warningSignList = warningSignService.queryAllWarningSign();
        model.addAttribute("warningSignList", warningSignList);
        return model;
    }
	
	/**
	 * 根据条件分页搜索预警信息
	 * @param queryConditionData
	 * @return
	 */
	@RequestMapping(value = "/searchWarningInfo" , method = RequestMethod.GET)
    public Model searchWarningInfo(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        Page page = new Page(queryConditionData);
        List<WarningSign> warningSignInfoList = 
        		warningSignService.searchWarningInfo(queryConditionData, page);
        model.addAttribute("warningSignInfoList", warningSignInfoList);
        model.addAttribute("page", page);
        return model;
    }
	
	/**
	 * 修改签收状态
	 * @param warningSign
	 * @return
	 */
	@RequestMapping(value = "/updateSignState", method = RequestMethod.POST)
    public Model updateSignState(SignView signView) {
    	int result = warningSignService.updateSignState(signView);
    	return ModelMapFactory.buildModelMap("result", result);
    }
}
