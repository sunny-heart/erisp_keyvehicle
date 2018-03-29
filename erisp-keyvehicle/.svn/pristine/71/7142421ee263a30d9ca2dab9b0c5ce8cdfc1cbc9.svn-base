package com.gkhb.keyvehicle.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.common.utils.SystemUtils;
import com.gkhb.keyvehicle.mapper.EarlyWarningInfoMapper;
import com.gkhb.keyvehicle.mapper.VehicleInfoMapper;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.VehicleInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.VehicleInfoView;

/**
 *	车辆信息服务接口实现类
 *	@author Colin
 *	@createTime 2017年7月3日 上午11:19:57
 */
@Service
public class VehicleInfoServiceImpl implements VehicleInfoService{
	
	protected Logger logger = LoggerFactory.getLogger(VehicleInfoServiceImpl.class);
	
	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;

	@Autowired
	private EarlyWarningInfoMapper earlyWarningInfoMapper;
	
	@Override
	public int addOrUpdateVehicleInfo(VehicleInfo vehicleInfo) {
		try {
            if (vehicleInfo.getId() == null || vehicleInfo.getId().equals("")) {
                //添加车辆
            	List<VehicleInfoView> list = vehicleInfoMapper.queryVehicle();
            	for (VehicleInfoView vehicleInfo2 : list) {
					if(vehicleInfo.getPlateNumber().trim().equals(vehicleInfo2.getPlateNumber().trim())){
						return 2;
					}
				}
                String id = SystemUtils.createUuid();
                vehicleInfo.setId(id);
                vehicleInfoMapper.addVehicleInfo(vehicleInfo);
            }else {
            	vehicleInfoMapper.updateVehicleInfo(vehicleInfo);
            }
            return 1;
        } catch (Exception e) {
            if (vehicleInfo.getId() == null || vehicleInfo.getId().equals("")) {
                logger.error("车辆添加失败");
            }else {
                logger.error("车辆修改失败");
            }
            return 0;
        }
	}

	@Override
	public boolean deleteVehicleInfoById(String id) {
		try {
			System.out.println("进入了deleteVehicleInfoById");
			vehicleInfoMapper.deleteVehicleInfoById(id);
	        return true;
        } catch (Exception e) {
            logger.error("车辆删除失败");
            return false;
        }
	}

	@Override
	public VehicleInfo queryVehicleInfoById(String id) {
//		System.out.println("进入了queryVehicleInfoById");
//		System.out.println("-----"+vehicleInfoMapper.queryVehicleInfoById(id));
		return vehicleInfoMapper.queryVehicleInfoById(id);
	}

	@Override
	public List<VehicleInfoView> queryVehicleInfo(QueryConditionData queryConditionData,Page page) {
//		System.out.println("------"+vehicleInfoMapper.queryVehicleInfo(queryConditionData, page));
		String plateNumber = queryConditionData.getPlateNumber();
		if(null != plateNumber && !"".equals(plateNumber)){
			String plateNumberHead = plateNumber.substring(0, 2);
			String plateNumberBody = plateNumber.substring(2);
			queryConditionData.setPlateNumberHead(plateNumberHead);
			queryConditionData.setPlateNumberBody(plateNumberBody);
		}
		return vehicleInfoMapper.queryVehicleInfo(queryConditionData,page);
	}

	@Override
	public List<VehicleInfoView> searchVehicleInfo(QueryConditionData queryConditionData,Page page) {
		return vehicleInfoMapper.searchVehicleInfo(queryConditionData,page);
	}
	
	@Override
	public List<VehicleInfoView> searchVehicleInfoByPlateNumber(String plateNumber) {
		return vehicleInfoMapper.searchVehicleInfoByPlateNumber(plateNumber);
	}

	@Override
	public List<VehicleInfoView> queryVehicle() {
		return vehicleInfoMapper.queryVehicle();
	}

	@Override
	public List<VehicleInfoView> searchVehicleInfoByVehicleType(QueryConditionData queryConditionData,String vehicleType) {
		long start = new Date().getTime();
		List<VehicleInfoView> searchVehicleInfoByVehicleType = vehicleInfoMapper.searchVehicleInfoByVehicleType(queryConditionData, vehicleType);
//		List<VehicleInfoView> searchVehicleInfoByVehicleType = new ArrayList<>();
//		VehicleInfoView vehicleInfoView = new VehicleInfoView();
//		for (int i = 0; i < 500; i++) {
//			vehicleInfoView.setLatitude(30.64727472222222);
//			vehicleInfoView.setLongitude(104.15140277777778);
//			vehicleInfoView.setId( SystemUtils.createUuid());
//			vehicleInfoView.setVehicleType("新能源");
//			vehicleInfoView.setReportTime(new Date());
//			searchVehicleInfoByVehicleType.add(vehicleInfoView);
//		}
		long end = new Date().getTime();
		long time = end - start;
		System.out.println("-----time-----------------"+time);
		return searchVehicleInfoByVehicleType;
	}
	
	@Override
	public VehicleInfoView searchVehicleInfoOfOtherInfo(String plateNumber){
		List<VehicleInfoView> list = vehicleInfoMapper.searchVehicleInfoByPlateNumber(plateNumber);
		VehicleInfoView vehicleInfoView = new VehicleInfoView();
		if(list.size()>0){
			vehicleInfoView = list.get(0);
		}
		Integer countAccidentTotalByPlateNumber = vehicleInfoMapper.countAccidentTotalByPlateNumber(plateNumber);
		Integer countIllegalTotalByPlateNumber = vehicleInfoMapper.countIllegalTotalByPlateNumber(plateNumber);
		vehicleInfoView.setAccidentTotal(countAccidentTotalByPlateNumber!=null?countAccidentTotalByPlateNumber.intValue():0);
		vehicleInfoView.setIllegalTotal(countIllegalTotalByPlateNumber!=null?countIllegalTotalByPlateNumber.intValue():0);
		
		if(vehicleInfoView!=null){
			EarlyWarningInfo earlyWarningInfo = earlyWarningInfoMapper.queryEarlyWarningInfoByVehicleId(vehicleInfoView.getId());
			if(null != earlyWarningInfo && !"".equals(earlyWarningInfo)){
				vehicleInfoView.setWarningType(earlyWarningInfo.getWarningType());
				vehicleInfoView.setWarningEndTime(earlyWarningInfo.getWarningStartTime());
			}
		}
		return vehicleInfoView;
	}

	private static Map<String,VehicleInfo> plateNumberCache = new HashMap<>();

	@Override
	public VehicleInfo queryVehicleInfoByPlateNumber(String plateNumber) {
		VehicleInfo vehicleInfo = plateNumberCache.get(plateNumber);
		if(null == vehicleInfo){
			logger.info("no cached vehicle info :" +plateNumber );
			QueryConditionData queryConditionData = new QueryConditionData();
			queryConditionData.setPlate(plateNumber);
			vehicleInfo = vehicleInfoMapper.queryVehicleInfoByPlateNumber(queryConditionData);
			plateNumberCache.put(plateNumber,vehicleInfo);
		}
		return vehicleInfo;
	}

	@Override
	public List<VehicleInfoView> queryVehicleMotTest(
			QueryConditionData queryConditionData,Page page) {
		return vehicleInfoMapper.queryVehicleMotTest(queryConditionData,page);
	}

	@Override
	public boolean dealMotTest(String id) {
		return vehicleInfoMapper.dealMotTest(id);
	}

	@Override
	public Integer countAccidentTotalByPlateNumber(String plateNumber) {
		return vehicleInfoMapper.countAccidentTotalByPlateNumber(plateNumber);
	}

	@Override
	public Integer countIllegalTotalByPlateNumber(String plateNumber) {
		return vehicleInfoMapper.countIllegalTotalByPlateNumber(plateNumber);
	}

	@Override
	public boolean updateVehiclePic(VehicleInfo vehicleInfo) {
		return vehicleInfoMapper.updateVehiclePic(vehicleInfo);
	}

	@Override
	public List<VehicleInfoView> queryVehicleByConditions(
			QueryConditionData queryConditionData) {
		return vehicleInfoMapper.queryVehicleByConditions(queryConditionData);
	}

	@Override
	public List<VehicleInfoView> queryWarningVehicleInfo(QueryConditionData queryConditionData, Page page) {
		String plateNumber = queryConditionData.getPlateNumber();
		if(null != plateNumber && !"".equals(plateNumber)){
			String plateNumberHead = plateNumber.substring(0, 2);
			String plateNumberBody = plateNumber.substring(2);
			queryConditionData.setPlateNumberHead(plateNumberHead);
			queryConditionData.setPlateNumberBody(plateNumberBody);
		}
		return vehicleInfoMapper.queryWarningVehicleInfo(queryConditionData, page);
	}
	
}
