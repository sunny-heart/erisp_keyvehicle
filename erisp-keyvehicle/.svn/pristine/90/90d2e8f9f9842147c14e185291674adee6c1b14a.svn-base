package com.gkhb.keyvehicle.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.mapper.AccidentInfoMapper;
import com.gkhb.keyvehicle.model.AccidentInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	事故信息服务接口实现类
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午10:54:23
 */
@Service
public class AccidentInfoServiceImpl implements AccidentInfoService{
	
	protected Logger logger = LoggerFactory.getLogger(AccidentInfoServiceImpl.class);
	
	@Autowired
	private AccidentInfoMapper accidentInfoMapper;

	@Override
	public List<AccidentInfo> queryAccident() {
		return accidentInfoMapper.queryAccident();
	}

	@Override
	public AccidentInfo queryAccidentInfoById(String id) {
		return accidentInfoMapper.queryAccidentInfoById(id);
	}

	@Override
	public List<AccidentInfo> searchAccidentInfo(QueryConditionData queryConditionData, Page page) {
		return accidentInfoMapper.searchAccidentInfo(queryConditionData, page);
	}

	@Override
	public List<AccidentInfo> searchAccidentInfoNoPage(
			QueryConditionData queryConditionData) {
		return accidentInfoMapper.searchAccidentInfo(queryConditionData);
	}

}
