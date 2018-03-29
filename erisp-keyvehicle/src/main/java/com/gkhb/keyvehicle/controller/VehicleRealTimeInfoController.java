package com.gkhb.keyvehicle.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gkhb.keyvehicle.common.spring.BaseController;
import com.gkhb.keyvehicle.common.utils.JsonUtils;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.GPSData;
import com.gkhb.keyvehicle.model.VehicleInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.VehicleInfoView;
import com.gkhb.keyvehicle.model.view.VehicleRealTimeInfoView;
import com.gkhb.keyvehicle.service.EarlyWarningInfoService;
import com.gkhb.keyvehicle.service.VehicleInfoService;
import com.gkhb.keyvehicle.service.VehicleRealTimeInfoService;
import com.gkhb.keyvehicle.service.etl.WarningEtlService;

/**
 *	车辆实时信息控制器
 *	@author Colin
 *	@createTime 2017年7月4日 上午9:50:52
 */
@Controller
@RequestMapping("/vehicleRealTimeInfo")
public class VehicleRealTimeInfoController extends BaseController{
	
	@Autowired
	private VehicleRealTimeInfoService vehicleRealTimeInfoService;
	
	@Autowired
	private WarningEtlService warningEtlService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	@Autowired
	private EarlyWarningInfoService earlyWarningInfoService;

	/**
	 * 查询所有车辆实时信息
	 * @return
	 */
	@RequestMapping(value = "/queryVehicleRealTimeInfo" , method = RequestMethod.GET)
    public Model queryVehicleRealTimeInfo(QueryConditionData queryConditionData){
        Model model = new ExtendedModelMap();
        List<VehicleRealTimeInfoView> vehicleRealTimeInfoList = vehicleRealTimeInfoService.queryVehicleRealTimeInfo();
        model.addAttribute("vehicleRealTimeInfo", vehicleRealTimeInfoList);
        return model;
    }
	
	/**
	 * 查询某一辆车的实时信息
	 * @return
	 */
	@RequestMapping(value = "/queryOneVehicleRealTimeInfo" , method = RequestMethod.GET)
    public Model queryOneVehicleRealTimeInfo(String queryConditionParams){
        Model model = new ExtendedModelMap();
        String plateNumber = JsonUtils.getJsonString(queryConditionParams, "plateNumber");
        VehicleRealTimeInfoView oneVehicleRealTimeInfo = vehicleRealTimeInfoService.queryOneVehicleRealTimeInfo(plateNumber);
        //VehicleRealTimeInfoView vehicleDriveInfo = getVehicleDriveInfo(plateNumber);
        model.addAttribute("oneVehicleRealTimeInfo", oneVehicleRealTimeInfo);
        //model.addAttribute("vehicleDriveInfo", vehicleDriveInfo);
        return model;
    }
	
	/**
	 * 查车辆行驶时间和速度等
	 * @return
	 */
	@RequestMapping(value = "/queryVehicleDriveInfo" , method = RequestMethod.GET)
    public Model queryVehicleDriveInfo(String plateNumber){
		if(plateNumber==null || plateNumber.equals("")){return null;}
        Model model = new ExtendedModelMap();
        String id = "";
        
        VehicleInfo queryVehicleInfoByPlateNumber = vehicleInfoService.queryVehicleInfoByPlateNumber(plateNumber);
        if(null != queryVehicleInfoByPlateNumber){
        	EarlyWarningInfo queryEarlyWarningInfoByVehicleId = earlyWarningInfoService.queryEarlyWarningInfoByVehicleId(queryVehicleInfoByPlateNumber.getId());
        	if(null != queryEarlyWarningInfoByVehicleId){
        		id = queryEarlyWarningInfoByVehicleId.getId();//预警ID
        	}
        }
        //VehicleRealTimeInfoView vehicleDriveInfo = getVehicleDriveInfo(plateNumber);
        VehicleInfoView vehicleInfoView = vehicleInfoService.searchVehicleInfoOfOtherInfo(plateNumber);
    	model.addAttribute("vehicleInfo", vehicleInfoView);
        //model.addAttribute("vehicleDriveInfo", vehicleDriveInfo);
        model.addAttribute("warningId", id);
        return model;
    }
	
	//获取车辆速度等实时信息--已废弃
	public VehicleRealTimeInfoView getVehicleDriveInfo(String plateNumber){
		if(plateNumber==null || plateNumber.equals("")){return null;}
		Date nowDate = new Date();
        QueryConditionData condition = new QueryConditionData();
        condition.setPlateNumber(plateNumber);
        condition.setCurrentDate(nowDate);
        VehicleRealTimeInfoView vehicleDriveInfo = null;
        VehicleRealTimeInfoView vehicleDriveInfoTotal = null;
        VehicleRealTimeInfoView currentYearWarningTotal = null;
        VehicleInfo v = vehicleInfoService.queryVehicleInfoByPlateNumber(plateNumber);
        if(v!=null){condition.setVehicleId(v.getId());}
        vehicleDriveInfo = vehicleRealTimeInfoService.queryVehicleDriveInfo(condition);
        vehicleDriveInfoTotal = vehicleRealTimeInfoService.queryOneWarningTotal(condition);
        currentYearWarningTotal = vehicleRealTimeInfoService.queryCurrentYearWarningTotal(condition);
        if(vehicleDriveInfo==null){
        	vehicleDriveInfo = new VehicleRealTimeInfoView();
        	vehicleDriveInfo.setSpeed(0);
        	vehicleDriveInfo.setCourse(0);
        	vehicleDriveInfo.setMaxSpeed(0);
        }
        if(vehicleDriveInfoTotal==null){
        	vehicleDriveInfoTotal = new VehicleRealTimeInfoView();
        	vehicleDriveInfoTotal.setWarningTimes(0);
        }
        if (currentYearWarningTotal==null) {
        	currentYearWarningTotal = new VehicleRealTimeInfoView();
        	currentYearWarningTotal.setYearWarningTimes(0);
		}
        vehicleDriveInfo.setWarningTimes(vehicleDriveInfoTotal.getWarningTimes());
        long continueDriveTime = 0;
        if(vehicleDriveInfo!=null && vehicleDriveInfo.getReportTime()!=null){
        	GPSData data = new GPSData();
            data.setPlateNumber(plateNumber);
            data.setReportTime(vehicleDriveInfo.getReportTime());
            warningEtlService.getContinueDrivingTime(data);
        }
        vehicleDriveInfo.setContinueDriveTime(continueDriveTime);
		return vehicleDriveInfo;
	}
}
