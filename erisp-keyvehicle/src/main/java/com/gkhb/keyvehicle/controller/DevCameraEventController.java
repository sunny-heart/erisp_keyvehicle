package com.gkhb.keyvehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.common.spring.BaseController;
import com.gkhb.keyvehicle.model.DevCameraEvent;
import com.gkhb.keyvehicle.slave.service.DevCameraEventService;

/**
 *	卡口信息控制器
 *	@author chenxiaojie
 *	@createTime 2017年10月19日 下午7:14:58
 */
@Controller
@RequestMapping("/devCameraEvent")
public class DevCameraEventController extends BaseController{
	
	@Autowired
	private DevCameraEventService devCameraEventService;
	
	/**
     * 查询所有卡口信息
     * @return
     */
    @RequestMapping(value = "/query" , method = RequestMethod.GET)
    public Model query(){
        Model model = new ExtendedModelMap();
        List<DevCameraEvent> devCameraEventList = devCameraEventService.queryAll();
        model.addAttribute("devCameraEventList", devCameraEventList);
        return model;
    }
    
    /**
     * 根据设备id查询卡口信息
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/queryDevCameraById", method = RequestMethod.GET)
    public Model queryDevCameraById(String deviceId) {
    	Model model = new ExtendedModelMap();
    	DevCameraEvent devCameraEvent = devCameraEventService.queryDevCameraById(deviceId);
	    model.addAttribute("devCameraEvent", devCameraEvent);
	    return model;
    }

}
