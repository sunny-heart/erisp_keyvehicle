package com.gkhb.keyvehicle.model.view;

import com.gkhb.keyvehicle.model.VehicleHisTrajectoryInfo;

/**
 *	历史轨迹记录
 *	@author Colin
 *	@createTime 2017年7月24日 下午5:52:20
 */
public class HisTrajectoryView extends VehicleHisTrajectoryInfo{
	
	private String address;
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}

}
