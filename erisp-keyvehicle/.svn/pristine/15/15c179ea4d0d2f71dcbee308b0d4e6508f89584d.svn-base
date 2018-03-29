package com.gkhb.keyvehicle.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.mapper.IllegalInfoMapper;
import com.gkhb.keyvehicle.model.IllegalInfo;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 *	违法信息服务接口实现类
 *	@author chenxiaojie
 *	@createTime 2017年10月3日 上午10:59:09
 */
@Service
public class IllegalInfoServiceImpl implements IllegalInfoService{
	
	protected Logger logger = LoggerFactory.getLogger(IllegalInfoServiceImpl.class);
	
	@Autowired
	private IllegalInfoMapper illegalInfoMapper;

	@Override
	public List<IllegalInfo> queryIllegal() {
		return illegalInfoMapper.queryIllegal();
	}

	@Override
	public IllegalInfo queryIllegalInfoById(String id) {
		return illegalInfoMapper.queryIllegalInfoById(id);
	}

	@Override
	public List<IllegalInfo> searchIllegalInfo(QueryConditionData queryConditionData, Page page) {
		return illegalInfoMapper.searchIllegalInfo(queryConditionData, page);
	}

	@Override
	public List<IllegalInfo> searchIllegalInfoNoPage(
			QueryConditionData queryConditionData) {
		return illegalInfoMapper.searchIllegalInfo(queryConditionData);
	}

}
