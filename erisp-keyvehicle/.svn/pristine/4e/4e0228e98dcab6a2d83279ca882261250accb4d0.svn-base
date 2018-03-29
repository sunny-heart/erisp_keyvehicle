package com.gkhb.keyvehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.common.spring.BaseController;
import com.gkhb.keyvehicle.model.AscriptionCompany;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.service.AscriptionCompanyService;

/**
 * 所属企业控制器
 * 
 * @author eddy
 */
@Controller
@RequestMapping("/ascriptionCompany")
public class AscriptionCompanyController extends BaseController {

	@Autowired
	private AscriptionCompanyService ascriptionCompanyService;

	/**
	 * 查询出租车所属企业
	 * 
	 * @param queryConditionData
	 * @return
	 */
	@RequestMapping(value = "/queryAscriptionCompany", method = RequestMethod.GET)
	public Model queryAscriptionCompany(QueryConditionData queryConditionData) {
		Model model = new ExtendedModelMap();
		List<AscriptionCompany> ascriptionCompanyList = ascriptionCompanyService
				.queryAscriptionCompany(queryConditionData);
		model.addAttribute("ascriptionCompanyList", ascriptionCompanyList);
		return model;
	}

	/**
	 * 查询物流车所属企业
	 * 
	 * @param queryConditionData
	 * @return
	 */
	@RequestMapping(value = "/queryVehicleInfoAscriptionCompany", method = RequestMethod.GET)
	public Model queryVehicleInfoAscriptionCompany(QueryConditionData queryConditionData) {
		Model model = new ExtendedModelMap();
		List<AscriptionCompany> ascriptionCompanyList = ascriptionCompanyService
				.queryVehicleInfoAscriptionCompany(queryConditionData);
		model.addAttribute("ascriptionCompanyList", ascriptionCompanyList);
		return model;
	}

}
