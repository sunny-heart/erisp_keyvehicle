package com.gkhb.keyvehicle.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.mapper.EarlyWarningInfoMapper;
import com.gkhb.keyvehicle.mapper.TaxiInfoMapper;
import com.gkhb.keyvehicle.mapper.VehicleInfoMapper;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.TaxiInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.TaxiInfoView;


/**
 *	出租车信息服务接口实现类
 *	@author Colin
 */
@Service
public class TaxiInfoServiceImpl implements TaxiInfoService{
	
	protected Logger logger = LoggerFactory.getLogger(TaxiInfoServiceImpl.class);
	
	@Autowired
	private TaxiInfoMapper taxiInfoMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;
	
	@Autowired
	private EarlyWarningInfoMapper earlyWarningInfoMapper;
	
	@Override
	public List<TaxiInfoView> queryTaxiInfo(QueryConditionData queryConditionData, Page page) {
		String plateNumber = queryConditionData.getPlateNumber();
		if(null != plateNumber && !"".equals(plateNumber)){
			String plateNumberHead = plateNumber.substring(0, 2);
			String plateNumberBody = plateNumber.substring(2);
			queryConditionData.setPlateNumberHead(plateNumberHead);
			queryConditionData.setPlateNumberBody(plateNumberBody);
		}
		return taxiInfoMapper.queryTaxiInfo(queryConditionData,page);
	}
	
	@Override
	public TaxiInfoView searchVehicleInfoOfOtherInfo(String plateNumber){
		List<TaxiInfoView> list = taxiInfoMapper.searchTaxiInfoByPlateNumber(plateNumber);
		List<TaxiInfo> arrayList = new ArrayList<TaxiInfo>();
		for (TaxiInfoView taxiInfoView : list) {
			TaxiInfo taxiInfo = new TaxiInfo();
			taxiInfo.setStrName(taxiInfoView.getStrName());
			taxiInfo.setStrSex(taxiInfoView.getStrSex());
			arrayList.add(taxiInfo);
		}
		TaxiInfoView taxiInfoView = new TaxiInfoView();
		if(list.size()>0){
			taxiInfoView = list.get(0);
		}
		taxiInfoView.setTaxiInfoList(arrayList);
		Integer countAccidentTotalByPlateNumber = vehicleInfoMapper.countAccidentTotalByPlateNumber(plateNumber);
		Integer countIllegalTotalByPlateNumber = vehicleInfoMapper.countIllegalTotalByPlateNumber(plateNumber);
		taxiInfoView.setAccidentTotal(countAccidentTotalByPlateNumber!=null?countAccidentTotalByPlateNumber.intValue():0);
		taxiInfoView.setIllegalTotal(countIllegalTotalByPlateNumber!=null?countIllegalTotalByPlateNumber.intValue():0);
		
		if(taxiInfoView!=null){
			QueryConditionData queryConditionData = new QueryConditionData();
			queryConditionData.setPlateNumber(plateNumber);
			EarlyWarningInfo earlyWarningInfo = earlyWarningInfoMapper.queryEarlyWarningInfoByPlateNumber(queryConditionData);
			if(null != earlyWarningInfo && !"".equals(earlyWarningInfo)){
				taxiInfoView.setWarningType(earlyWarningInfo.getWarningType());
				taxiInfoView.setWarningEndTime(earlyWarningInfo.getWarningStartTime());
			}
		}
		return taxiInfoView;
	}

	@Override
	public List<TaxiInfoView> searchTaxiInfoByPlateNumber(String plateNumber) {
		return taxiInfoMapper.searchTaxiInfoByPlateNumber(plateNumber);
	}

	@Override
	public TaxiInfo queryTaxiInfoByPlateNumber(QueryConditionData queryConditionData) {
		return taxiInfoMapper.queryTaxiInfoByPlateNumber(queryConditionData);
	}

	@Override
	public List<TaxiInfo> queryAllTaxiInfo() {
		return taxiInfoMapper.queryAllTaxiInfo();
	}
}
