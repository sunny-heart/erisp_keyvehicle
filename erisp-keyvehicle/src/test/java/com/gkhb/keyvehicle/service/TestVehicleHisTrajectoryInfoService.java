package com.gkhb.keyvehicle.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gkhb.keyvehicle.BaseTestCase;
import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.common.utils.DateUtils;
import com.gkhb.keyvehicle.common.utils.JeckSonUtils;
import com.gkhb.keyvehicle.model.VehicleHisTrajectoryInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.mysql.fabric.xmlrpc.base.Data;

/**
 *	车辆历史轨迹信息类单元测试
 *	@author Colin
 *	@createTime 2017年7月4日 上午10:57:48
 */
@RunWith(Parameterized.class)
public class TestVehicleHisTrajectoryInfoService extends BaseTestCase{
	
	@Autowired
	private VehicleHisTrajectoryInfoService vehicleHisTrajectoryInfoService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	private VehicleHisTrajectoryInfo vehicleHisTrajectoryInfo;
	
	/**
	 * 期望值
	 */
	private String expected;

	public TestVehicleHisTrajectoryInfoService(String expected,VehicleHisTrajectoryInfo vehicleHisTrajectoryInfo) {
		this.vehicleHisTrajectoryInfo = vehicleHisTrajectoryInfo;
		this.expected = expected;
	}
	
	@Parameters
	public static Collection<Object[]> data() throws ParseException, JsonProcessingException {
		VehicleHisTrajectoryInfo vehicleHisTrajectoryInfo1 = new VehicleHisTrajectoryInfo();
		vehicleHisTrajectoryInfo1.setId("1");
		vehicleHisTrajectoryInfo1.setLatitude(30.818449);
		vehicleHisTrajectoryInfo1.setLongitude(104.241908);
		vehicleHisTrajectoryInfo1.setPlateNumber("1");
		vehicleHisTrajectoryInfo1.setReportTime(DateUtils.formatDate("2017-07-03 15:35:27", DateUtils.YYYY_MM_DD_HH_MM_SS));
		vehicleHisTrajectoryInfo1.setSpeed(30);
		String json1 = JeckSonUtils.object2Json(vehicleHisTrajectoryInfo1);
		return Arrays.asList(new Object[][] { { json1, vehicleHisTrajectoryInfo1 }});
	}
	
	@Test
	public void test() throws JsonProcessingException {
//		vehicleHisTrajectoryInfoService.searchVehicleHisTrajectoryInfo(null, "川AS0896");
		QueryConditionData queryConditionData = new QueryConditionData();
//		vehicleInfoService.searchVehicleInfoByVehicleType(queryConditionData, "普通");
		queryConditionData.setPlateNumber("川AS0896");
		
		System.out.println(DateUtils.formatDateToString(new Date(2017, 5, 1, 00, 00, 00), DateUtils.YYYY_MM_DD_HH_MM_SS));
		queryConditionData.setStartTime(DateUtils.formatDateToString(new Date(2017, 5, 1, 00, 00, 00), DateUtils.YYYY_MM_DD_HH_MM_SS));
		queryConditionData.setEndTime(DateUtils.formatDateToString(new Date(2017, 9, 7, 00, 00, 00), DateUtils.YYYY_MM_DD_HH_MM_SS));
//		vehicleHisTrajectoryInfoService.queryLocations(queryConditionData);
	}
	

}
