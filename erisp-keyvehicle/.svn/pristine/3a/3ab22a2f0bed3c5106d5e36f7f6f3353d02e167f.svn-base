package com.gkhb.keyvehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.common.spring.BaseController;
import com.gkhb.keyvehicle.common.spring.ModelMapFactory;
import com.gkhb.keyvehicle.model.view.DisposalProcessView;
import com.gkhb.keyvehicle.service.DisposalProcessService;

/**
 * 预警处置流程
 */
@Controller
@RequestMapping(("/disposalProcess"))
public class DisposalProcessController extends BaseController {
    
	@Autowired
	DisposalProcessService disposalProcessService;

    /**
     * 更新抄报状态
     * @return
     */
    @RequestMapping(value = "/updateJgCcState", method = RequestMethod.POST)
    public Model updateJgCcState(DisposalProcessView disposalProcessView) {
    	int result = disposalProcessService.updateJgCcState(disposalProcessView);
    	return ModelMapFactory.buildModelMap("result", result);
    }
   
}
