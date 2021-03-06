package com.gkhb.keyvehicle.service.etl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.service.VehicleOnlineStateService;

/**
 * 车辆在线状态拉取线程类
 * 
 * @author eddy
 */
public class ETLRunnableForVehicleState implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ETLRunnableForVehicleState.class);

	private VehicleOnlineStateService vehicleOnlineStateService;
	
    public ETLRunnableForVehicleState(VehicleOnlineStateService vehicleOnlineStateService) {
        this.vehicleOnlineStateService = vehicleOnlineStateService;
    }

    @Override
    public void run() {
    	LOG.info("------在线离线更新--定时任务开始了-------");
    	QueryConditionData condition = new QueryConditionData();
    	Date currentDate = new Date();
    	condition.setCurrentDate(currentDate);
    	vehicleOnlineStateService.addOrUpdateVehicleOnlineState(condition);
    }

}
