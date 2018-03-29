package com.gkhb.keyvehicle.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.mapper.TaxiDriverInfoMapper;
import com.gkhb.keyvehicle.model.TaxiDriverInfo;


/**
 *	出租车驾驶员信息服务接口实现类
 *	@author Eddy
 */
@Service
public class TaxiDriverInfoServiceImpl implements TaxiDriverInfoService{
	
	protected Logger logger = LoggerFactory.getLogger(TaxiDriverInfoServiceImpl.class);
	
	@Autowired
	private TaxiDriverInfoMapper taxiDriverInfoMapper;

	@Override
	public List<TaxiDriverInfo> queryTaxiDriverInfo() {
		return taxiDriverInfoMapper.queryTaxiDriverInfo();
	}
	
}
