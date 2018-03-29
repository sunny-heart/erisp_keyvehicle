package com.gkhb.keyvehicle.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gkhb.keyvehicle.BaseTestCase;
import com.gkhb.keyvehicle.common.utils.JeckSonUtils;
import com.gkhb.keyvehicle.model.VehicleInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	车辆信息类单元测试
 *	@author Colin
 *	@createTime 2017年7月3日 下午12:28:03
 */
@RunWith(Parameterized.class)
public class TestVehicleInfoService extends BaseTestCase{
	
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	private VehicleInfo vehicleInfo;
	
	/**
	 * 期望值
	 */
	private String expected;

	public TestVehicleInfoService(String expected,VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
		this.expected = expected;
	}
	
	@Parameters
	public static Collection<Object[]> data() throws ParseException, JsonProcessingException {
		VehicleInfo vehicleInfo1 = new VehicleInfo();
		vehicleInfo1.setId("11");
		vehicleInfo1.setApplyCompany("1");
		vehicleInfo1.setAscriptionCompany("1");
		vehicleInfo1.setAuthorizedLoad(10);
		vehicleInfo1.setContacts("1");
		vehicleInfo1.setPhoneNumber("1");
		vehicleInfo1.setPlateNumber("川AS2686");
//		vehicleInfo1.setRegistrationDate(new Date("2017-07-03"));
		vehicleInfo1.setSystemNumber("1");
		vehicleInfo1.setVehicleType("1");
//		List<VehicleInfo> list1 = new ArrayList<VehicleInfo>(); 
//		list1.add(vehicleInfo1);
		System.out.println(vehicleInfo1);
		String json1 = JeckSonUtils.object2Json(vehicleInfo1);
		return Arrays.asList(new Object[][] { { json1, vehicleInfo1 }});
	}
	
	@Before
	public void beforeAddTest(){
		vehicleInfoService.addOrUpdateVehicleInfo(vehicleInfo);
	}

	@Test
	public void testAdd() throws JsonProcessingException {
//		vehicleInfoService.queryVehicleInfo();
//		String comparedVehicleType = ComparedVehicleTypeUtils.comparedVehicleType("A");
//		System.out.println("============AAAAAAAAAAA"+comparedVehicleType);
		QueryConditionData queryConditionData = new QueryConditionData();
		vehicleInfoService.searchVehicleInfoByVehicleType(queryConditionData, "");
	}
	
	@After
	public void afterAddTest(){
		vehicleInfoService.deleteVehicleInfoById(vehicleInfo.getId());
	}
}
