package com.gkhb.keyvehicle.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.GPSData;
import com.gkhb.keyvehicle.model.HistData;
import com.gkhb.keyvehicle.model.RealTimeData;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 * 历史GPS位置Mapper
 * 
 * @author 张顺江
 * @createTime 2017年9月7日 上午10:17:12
 */
@Repository
public interface HistDataMapper {
    /**
     * 添加历史GPS位置
     * 
     * @param HistData
     */
    public void addHistData(HistData histData);

    /**
     * 查询所有历史GPS位置
     * 
     * @return
     */
    public List<HistData> queryHistData();
    
    /**
	 * 查询某段时间内某辆车的位置信息
	 */
	List<GPSData> queryLocationsByHistData(@Param("queryConditionData")QueryConditionData queryConditionData);

    /**
     * 修改历史GPS位置
     * 
     * @param HistData
     * @return
     */
    public int updateHistData(HistData histData);

    /**
     * 批量添加历史GPS位置
     * @param histDataList
     * @return
     */
    public int addHistDataBatch(RealTimeData realTimeData);

    /**
     * 根据清洗状态查询GPS数据
     * @param EtlState
     * @return
     */
    public List<HistData> queryHistDataByEtlState(int etlState);

    /**
     * 批量更新清理状态为已清理
     * @param histDataList
     */
    public void updateHistDataToEtlState1(List<HistData> histDataList);

    /**
     * 查询历史GPS位置中速度为0的数据
     * @param gpsData
     * @return List<HistData>
     */
    public List<HistData> queryHistDataForSpeed0(@Param("gpsData")GPSData gpsData);
    
}
