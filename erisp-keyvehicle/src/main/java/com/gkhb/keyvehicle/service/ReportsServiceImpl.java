package com.gkhb.keyvehicle.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.mapper.ReportsMapper;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.ReportsView;

/**
 *	统计报表
 */
@Service
public class ReportsServiceImpl implements ReportsService{
	
	protected Logger logger = LoggerFactory.getLogger(ReportsServiceImpl.class);
	
	@Autowired
	private ReportsMapper reportsMapper;

	@Override
	public List<ReportsView> countByTypes(QueryConditionData queryConditionData) {
		return reportsMapper.countByTypes(queryConditionData);
	}

	@Override
	public List<ReportsView> countByDepartments(
			QueryConditionData queryConditionData) {
		return reportsMapper.countByDepartments(queryConditionData);
	}

	@Override
	public List<ReportsView> countAllWarningBySuboffices(
			QueryConditionData queryConditionData) {
		return reportsMapper.countAllWarningBySuboffices(queryConditionData);
	}

	@Override
	public List<ReportsView> countAllWarningByCompany(
			QueryConditionData queryConditionData) {
		return reportsMapper.countAllWarningByCompany(queryConditionData);
	}

}
