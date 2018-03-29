package com.gkhb.keyvehicle.mapper;

import com.gkhb.keyvehicle.model.TravelTime;
import com.gkhb.keyvehicle.model.WarningRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 预警判断
 * @author ranbingcheng
 *
 */
@Repository
public interface WarnRuleMapper {
	/**
	 * 根据车辆ID和车辆行驶的年月日、时分秒去数据库查询有没有在时间规则表里面
	 * @param vehicleId 车辆ID
	 * @param reportTimeOfTime 时分秒
	 * @param reportTimeOfDate 年月日
	 * @return 时间规则对象
	 */
	public TravelTime queryTravelTimeByvehicleIdAndTime(@Param("vehicleId")String vehicleId,@Param("reportTimeOfTime")String reportTimeOfTime,@Param("reportTimeOfDate")String reportTimeOfDate);

	/**
	 * 保存
	 * @param warningRule
	 * @return
	 */
	int add(WarningRule warningRule);

	/**
	 * 查询预警信息
	 *
	 * @param warningRule
	 * @return
	 */
	List<WarningRule> queryWarningRule(WarningRule warningRule);

	/**
	 * 更新预警信息
	 * @param warningRule
	 * @return
	 */
	int update(WarningRule warningRule);

	/**
	 * 删除预警信息
	 * @param warningRule
	 * @return
	 */
    int delete(WarningRule warningRule);
}
