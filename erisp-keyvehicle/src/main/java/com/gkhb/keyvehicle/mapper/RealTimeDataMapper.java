package com.gkhb.keyvehicle.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.GPSData;
import com.gkhb.keyvehicle.model.RealTimeData;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 * 实时GPS位置Mapper
 * 
 * @author 张顺江
 * @createTime 2017年9月7日 上午10:17:12
 */
@Repository
public interface RealTimeDataMapper {
    /**
     * 添加实时GPS位置
     * 
     * @param RealTimeData
     */
    public void addRealTimeData(RealTimeData realTimeData);

    /**
     * 查询所有实时GPS位置
     * 
     * @return
     */
    public List<RealTimeData> queryRealTimeData();
    
    /**
	 * 查询某段时间内某辆车的位置信息
	 */
	List<GPSData> queryLocationsByRealTimeData(@Param("queryConditionData")QueryConditionData queryConditionData);

    /**
     * 修改实时GPS位置
     * 
     * @param RealTimeData
     * @return
     */
    public int updateRealTimeData(RealTimeData realTimeData);

    /**
     * 根据时间段查询GPS数据
     * @param beforeDate 开始时间
     * @param newDate 结束时间
     */
    public List<RealTimeData> queryRealTimeDataByTime(@Param("newDate")Date newDate);
    
    /**
     * 查询根据时间段删除GPS数据
     * @param beforeDate 开始时间
     * @param newDate 结束时间
     */
    public int deleteRealTimeDataByTime(@Param("newDate")Date newDate);

    /**
     * 根据清洗状态查询GPS数据
     * @param EtlState 清洗状态
     * @return List<RealTimeData>
     */
    public List<RealTimeData> queryRealTimeDataByEtlState(@Param("etlState")int etlState);

    /**
     * 批量更新清理状态为已清理
     * @param realTimeDataList
     */
    public void updateRealTimeDataToEtlState1(List<RealTimeData> realTimeDataList);

    /**
     * 查询实时GPS位置中速度为0的数据
     * @param gpsData
     * @return
     */
    public List<RealTimeData> queryRealTimeDataForSpeed0(@Param("gpsData")GPSData gpsData);

}
