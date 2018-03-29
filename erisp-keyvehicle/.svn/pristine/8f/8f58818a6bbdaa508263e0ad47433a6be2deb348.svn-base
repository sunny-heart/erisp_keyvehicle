package com.gkhb.keyvehicle.service;

import com.gkhb.keyvehicle.mapper.WarnRuleMapper;
import com.gkhb.keyvehicle.model.RealTimeData;
import com.gkhb.keyvehicle.model.TravelTime;
import com.gkhb.keyvehicle.model.WarningRule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WarnRuleServiceImpl implements WarnRuleService {
	@Autowired
	private WarnRuleMapper warnRuleMapper;

	@Override
	public TravelTime TimeRuleWarn(RealTimeData data) {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date reportTime = data.getReportTime();
		String reportTimeOfDate = format1.format(reportTime);
		SimpleDateFormat format2 = new SimpleDateFormat("HH-mm-ss");
		String reportTimeOfTime = format2.format(reportTime);
		String vehicleId = data.getId();
		TravelTime result = warnRuleMapper.queryTravelTimeByvehicleIdAndTime(vehicleId, reportTimeOfTime,
				reportTimeOfDate);
		return result;
	}

	@Override
	public int add(WarningRule warningRule) {
		if (warningRule == null || StringUtils.isBlank(warningRule.getId())){
			throw new RuntimeException("Object is null or id is empty");
		}
		return warnRuleMapper.add(warningRule);
	}

	@Override
	public List<WarningRule> queryWarningRule(WarningRule warningRule) {
		if (warningRule == null) {
			return new ArrayList<>(0);
		}
		return warnRuleMapper.queryWarningRule(warningRule);
	}

	@Override
	public int update(WarningRule warningRule) {
		return warnRuleMapper.update(warningRule);
	}

	@Override
	public int delete(WarningRule warningRule) {
		if (warningRule == null || StringUtils.isBlank(warningRule.getId())){
			throw new RuntimeException("Object is null or id is empty");
		}
		return warnRuleMapper.delete(warningRule);
	}
}
