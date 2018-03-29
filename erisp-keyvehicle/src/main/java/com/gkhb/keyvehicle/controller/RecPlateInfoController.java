package com.gkhb.keyvehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.RecPlateInfoView;
import com.gkhb.keyvehicle.slave.service.RecPlateInfoService;

@Controller
@RequestMapping(("/recPlateInfo"))
public class RecPlateInfoController {

	@Autowired
    private RecPlateInfoService recPlateInfoService;
  
	/**
	 * 查询某段时间内的卡口信息(含卡口gps坐标)
	 * @param queryConditionData
	 * @return
	 */
	@RequestMapping(value = "/queryAllRecPlateInfo", method = RequestMethod.GET)
	public Model queryAllRecPlateInfo(QueryConditionData queryConditionData){
		Model model = new ExtendedModelMap();
    	List<RecPlateInfoView> recPlateInfoList = recPlateInfoService.queryAll(queryConditionData);
    	model.addAttribute("recPlateInfoList",recPlateInfoList);
		return model;
    }
	
	/**
	 * 查询某段时间内的过车卡口信息(无卡口设备gps坐标)
	 * @param queryConditionData
	 * @return
	 */
	@RequestMapping(value = "/queryRecPlateInfo", method = RequestMethod.GET)
	public Model queryRecPlateInfo(QueryConditionData queryConditionData){
		Model model = new ExtendedModelMap();
		List<RecPlateInfoView> recPlateInfoList = recPlateInfoService.queryRecPlateInfo(queryConditionData);
		model.addAttribute("recPlateInfoList",recPlateInfoList);
		return model;
	}

}
