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
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.TaxiInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.TaxiInfoView;
import com.gkhb.keyvehicle.service.EarlyWarningInfoService;
import com.gkhb.keyvehicle.service.TaxiInfoService;
import com.gkhb.keyvehicle.service.VehicleInfoService;


/**
 *	出租车信息控制器
 *	@author Colin
 */
@Controller
@RequestMapping("/taxiInfo")
public class TaxiInfoController extends BaseController{
	
	@Autowired
	private TaxiInfoService taxiInfoService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	@Autowired
	private EarlyWarningInfoService earlyWarningInfoService;
	
	/**
	 * 分页查询出租车信息
	 * @param queryConditionData
	 * @return
	 */
    @RequestMapping(value = "/queryTaxiInfo" , method = RequestMethod.GET)
    public Model queryVehicleInfo(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        Page page = new Page(queryConditionData);
        List<TaxiInfoView> taxiInfoList = taxiInfoService.queryTaxiInfo(queryConditionData,page);
        for (TaxiInfoView taxiInfoView : taxiInfoList) {
			Integer accidentTotal = vehicleInfoService.countAccidentTotalByPlateNumber(taxiInfoView.getStrVehicleNo());
			Integer illegalTotal = vehicleInfoService.countIllegalTotalByPlateNumber(taxiInfoView.getStrVehicleNo());
			taxiInfoView.setAccidentTotal(accidentTotal!=null?accidentTotal.intValue():0);
			taxiInfoView.setIllegalTotal(illegalTotal!=null?illegalTotal.intValue():0);
		}
        model.addAttribute("taxiInfo", taxiInfoList);
        model.addAttribute("page", page);
        return model;
    }
    
    
    /**
	 * 查询出租车的车辆信息和预警信息
	 * @return
	 */
	@RequestMapping(value = "/queryTaxiDriveInfo" , method = RequestMethod.GET)
    public Model queryVehicleDriveInfo(String plateNumber){
		if(plateNumber==null || plateNumber.equals("")){return null;}
        Model model = new ExtendedModelMap();
        String id = "";
        
        QueryConditionData queryConditionData = new QueryConditionData();
        queryConditionData.setPlateNumber(plateNumber);
        TaxiInfo taxiInfo = taxiInfoService.queryTaxiInfoByPlateNumber(queryConditionData);
        if(null != taxiInfo){
        	EarlyWarningInfo queryEarlyWarningInfoByVehicleId = earlyWarningInfoService.queryEarlyWarningInfoByPlateNumber(queryConditionData);
        	if(null != queryEarlyWarningInfoByVehicleId){
        		id = queryEarlyWarningInfoByVehicleId.getId();//预警ID
        	}
        }
        //VehicleRealTimeInfoView vehicleDriveInfo = getVehicleDriveInfo(plateNumber);
        TaxiInfoView taxiInfoView = taxiInfoService.searchVehicleInfoOfOtherInfo(plateNumber);
    	model.addAttribute("taxiInfo", taxiInfoView);
        //model.addAttribute("vehicleDriveInfo", vehicleDriveInfo);
        model.addAttribute("warningId", id);
        return model;
    }
	
	
}
