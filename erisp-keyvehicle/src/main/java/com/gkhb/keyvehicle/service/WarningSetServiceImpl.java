package com.gkhb.keyvehicle.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.common.utils.SystemUtils;
import com.gkhb.keyvehicle.mapper.FatigueDrivingMapper;
import com.gkhb.keyvehicle.mapper.SpeedMapper;
import com.gkhb.keyvehicle.mapper.TravelRouteMapper;
import com.gkhb.keyvehicle.mapper.TravelTimeMapper;
import com.gkhb.keyvehicle.mapper.WarningSetMapper;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.WarningSetView;

/**
 * 预警设置业务类
 * @author WeiGuo
 * @data 2017年9月23日下午5:13:22
 */
@Service
public class WarningSetServiceImpl implements WarningSetService {
    @Autowired
 	private WarningSetMapper warningSetMapper;
    
    @Autowired
    private TravelRouteMapper travelRouteMapper;
    
    @Autowired
    private TravelTimeMapper travelTimeMapper;
    
    @Autowired
    private SpeedMapper speedMapper;
    
    @Autowired
    private FatigueDrivingMapper fatigueDrivingMapper;
	@Override
	public int addWarning(WarningSetView warningData) {
		int count=0;
	  warningData.setId(SystemUtils.createUuid());
	  warningData.setState(0);
      String warningType=warningData.getWarningType();
      if(warningType.equals("超速")){
    	count=  warningSetMapper.addWarningOverSpeed(warningData);
      }else if(warningType.equals("违规时间")){
    	  count= warningSetMapper.addWarningWrongTime(warningData);
      }else if(warningType.equals("违规路线")){
    	  count= warningSetMapper.addWarningWrongRoute(warningData);
      }else if(warningType.equals("疲劳驾驶")){
    	  count= warningSetMapper.addWarningFatigue(warningData);
      }
      return count;
	}
	
	@Override
    public List<WarningSetView> queryWarningInfoList(QueryConditionData queryConditionData, Page page) {
        // 1：违规路线、2：违规时间、3：超速、4：疲劳驾驶
        String warningType = queryConditionData.getWarningType();
        List<WarningSetView> warningInfoList = new ArrayList<WarningSetView>();
        if(StringUtils.isBlank(warningType)){
            return warningSetMapper.queryWarningInfo(queryConditionData,page);
        }
        switch (warningType) {
        case "1":
            warningInfoList = travelRouteMapper.queryWarningInfo(queryConditionData,page);
            break;
        case "2":
            warningInfoList = travelTimeMapper.queryWarningInfo(queryConditionData,page);
            break;
        case "3":
            warningInfoList = speedMapper.queryWarningInfo(queryConditionData,page);
            break;
        case "4":
            warningInfoList = fatigueDrivingMapper.queryWarningInfo(queryConditionData,page);
            break;
            
        // warningType为空或其他情况，查询所有预警类型
        default:
            warningInfoList = warningSetMapper.queryWarningInfo(queryConditionData,page);
            break;
        }
        
        return warningInfoList;
    }

    @Override
    public int deleteWarningInfo(String id, String warningType) {
        int count = 0;
        switch (warningType) {
        case "违规路线":
            count = travelRouteMapper.deleteWarningInfo(id);
            break;
        case "违规时间":
            count = travelTimeMapper.deleteWarningInfo(id);
            break;
        case "超速":
            count = speedMapper.deleteWarningInfo(id);
            break;
        case "疲劳驾驶":
            count = fatigueDrivingMapper.deleteWarningInfo(id);
            break;
            
        default:
            break;
        }
        return count;
    }
    
    @Override
	public int updateWarning(WarningSetView warningData) {
		 int count=0;
		 String warningType=warningData.getWarningType();
	      if(warningType.equals("超速")){
	    	count=  warningSetMapper.updateWarningOverSpeed(warningData);
	      }else if(warningType.equals("违规时间")){
	    	  count= warningSetMapper.updateWarningWrongTime(warningData);
	      }else if(warningType.equals("违规路线")){
	    	  count= warningSetMapper.updateWarningWrongRoute(warningData);
	      }else if(warningType.equals("疲劳驾驶")){
	    	  count= warningSetMapper.updateWarningFatigue(warningData);
	      }
	      return count;
	}

}
