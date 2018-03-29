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
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.WarningSetView;
import com.gkhb.keyvehicle.service.WarningSetService;

/**
 * 预警设置控制器
 * @author WeiGuo
 * @data 2017年9月23日下午5:09:48
 */
@Controller
@RequestMapping(("/warningSet"))
public class WarningSetController extends BaseController {
    @Autowired
	private WarningSetService  warningSetService;
	 /**
	  * 添加预警信息
	 * @param warningData
	 * @return
	 */
	@RequestMapping(value = "/addWarning", method = RequestMethod.GET)
	public Model addWarning(WarningSetView warningData){
		Model model =new ExtendedModelMap();
		int count=warningSetService.addWarning(warningData);
		model.addAttribute("result", count);
		return model;
	}
	
	/**
	 * 修改预警信息
	 * @param warningData
	 * @return
	 */
	@RequestMapping(value = "/updateWarning", method = RequestMethod.GET)
	public Model updateWarning(WarningSetView warningData){
		 Model model = new ExtendedModelMap();
		 int count=warningSetService.updateWarning(warningData);
		 model.addAttribute("result", count);
		 return model;
	}
	
	@RequestMapping(value = "/queryWarningInfoList", method = RequestMethod.GET)
    public Model queryWarningInfoList(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        Page page = new Page(queryConditionData);
        List<WarningSetView> warningDisposalViewList = warningSetService.queryWarningInfoList(queryConditionData, page);
        model.addAttribute("warningInfoList", warningDisposalViewList);
        model.addAttribute("total", page.getCount());
        model.addAttribute("currentPage", page.getPage());
        model.addAttribute("pages", page.getPages());
        return model;
    }
	
	@RequestMapping(value = "/deleteWarningInfo", method = RequestMethod.POST)
    public Model deleteWarningInfo(String id,String warningType){
        Model model = new ExtendedModelMap();
        int count = warningSetService.deleteWarningInfo(id,warningType);
        model.addAttribute("count", count);
        return model;
    }

}
