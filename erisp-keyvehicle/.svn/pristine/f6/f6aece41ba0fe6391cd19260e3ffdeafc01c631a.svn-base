package com.gkhb.keyvehicle.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.mapper.AscriptionCompanyMapper;
import com.gkhb.keyvehicle.model.AscriptionCompany;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 * 所属企业服务接口实现类
 * 
 * @author eddy
 */
@Service
public class AscriptionCompanyServiceImpl implements AscriptionCompanyService {

	protected Logger logger = LoggerFactory.getLogger(AscriptionCompanyServiceImpl.class);

	@Autowired
	private AscriptionCompanyMapper ascriptionCompanyMapper;

	@Override
	public List<AscriptionCompany> queryAscriptionCompany(QueryConditionData queryConditionData) {
		return ascriptionCompanyMapper.queryAscriptionCompany(queryConditionData);
	}

	@Override
	public List<AscriptionCompany> queryVehicleInfoAscriptionCompany(QueryConditionData queryConditionData) {
		return ascriptionCompanyMapper.queryVehicleInfoAscriptionCompany(queryConditionData);
	}

}
