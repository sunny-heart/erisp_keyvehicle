package com.gkhb.keyvehicle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.common.Constants;
import com.gkhb.keyvehicle.common.spring.BaseController;
import com.gkhb.keyvehicle.common.spring.ModelMapFactory;
import com.gkhb.keyvehicle.common.utils.HttpClientUtil;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 * 短信发送
 */
@Controller
@RequestMapping(("/sms"))
public class SMSController extends BaseController {

    /**
     * 发送短信通知
     * @return
     */
    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public Model sendSMS(QueryConditionData queryConditionData) {
    	HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF发送
		int result = client.sendMsgUtf8(Constants.SMS_USER, Constants.SMS_KEY, 
				queryConditionData.getSmsText(), queryConditionData.getSmsMob());
		return ModelMapFactory.buildModelMap("result", result);
    }
   
}
