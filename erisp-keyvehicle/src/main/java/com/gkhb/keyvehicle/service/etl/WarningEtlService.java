package com.gkhb.keyvehicle.service.etl;

import java.util.List;

import com.gkhb.keyvehicle.model.GPSData;
import com.gkhb.keyvehicle.model.VehicleInfo;

/**
 * 预警判断接口
 * @author 张顺江
 * @createTime 2017年9月8日 下午2:52:04
 */
public interface WarningEtlService {

    /**
     * 路线预警清洗
     */
    void routeWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo);
    /**
     * 速度预警清洗
     */
    void speedWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo);
    /**
     * 时间预警清洗
     */
    void timeWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo);
    /**
     * 疲劳驾驶预警清洗
     */
    void fatigueDrivingWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo);
    
    /**
     * 连续行驶时间
     * @param data
     * @return
     */
    long getContinueDrivingTime(GPSData data);

    /**
     * gps数据告警
     * @param gpsDataList gps数据列表
     */
    void warning(List<GPSData> gpsDataList);
}
