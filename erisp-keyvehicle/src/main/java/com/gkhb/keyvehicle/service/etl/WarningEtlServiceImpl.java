package com.gkhb.keyvehicle.service.etl;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.common.utils.DateUtils;
import com.gkhb.keyvehicle.common.utils.SystemUtils;
import com.gkhb.keyvehicle.mapper.DisposalProcessMapper;
import com.gkhb.keyvehicle.mapper.EarlyWarningInfoMapper;
import com.gkhb.keyvehicle.mapper.FatigueDrivingMapper;
import com.gkhb.keyvehicle.mapper.HistDataMapper;
import com.gkhb.keyvehicle.mapper.RealTimeDataMapper;
import com.gkhb.keyvehicle.mapper.SpeedMapper;
import com.gkhb.keyvehicle.mapper.TravelRouteMapper;
import com.gkhb.keyvehicle.mapper.TravelTimeMapper;
import com.gkhb.keyvehicle.model.DisposalProcess;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.FatigueDriving;
import com.gkhb.keyvehicle.model.GPSData;
import com.gkhb.keyvehicle.model.HistData;
import com.gkhb.keyvehicle.model.RealTimeData;
import com.gkhb.keyvehicle.model.Speed;
import com.gkhb.keyvehicle.model.TravelRoute;
import com.gkhb.keyvehicle.model.TravelTime;
import com.gkhb.keyvehicle.model.VehicleInfo;
import com.gkhb.keyvehicle.service.VehicleInfoService;

/**
 * 预警判断接口
 *
 * @author 张顺江
 * @createTime 2017年9月8日 下午2:52:04
 */
@Service("warningEtlService")
public class WarningEtlServiceImpl implements WarningEtlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarningEtlServiceImpl.class);

    @Autowired
    private RealTimeDataMapper realTimeDataMapper;

    @Autowired
    private HistDataMapper histDataMapper;

    @Autowired
    private EarlyWarningInfoMapper earlyWarningInfoMapper;

    @Autowired
    private TravelRouteMapper travelRouteMapper;

    @Autowired
    private TravelTimeMapper travelTimeMapper;

    @Autowired
    private DisposalProcessMapper disposalProcessMapper;

    @Autowired
    private VehicleInfoService vehicleInfoService;

    @Autowired
    private SpeedMapper speedMapper;

    @Autowired
    private FatigueDrivingMapper fatigueDrivingMapper;

    @Override
    public void warning(List<GPSData> gpsDataList) {
        // 遍历每一个GPS实时位置
        for (GPSData gpsData : gpsDataList) {
            try {
                // 获取车辆信息
                VehicleInfo vehicleInfo = vehicleInfoService.queryVehicleInfoByPlateNumber(gpsData.getPlateNumber());
                if (vehicleInfo == null) {
                    LOGGER.error("vehicleInfo is null,PlateNumber is :" + gpsData.getPlateNumber());
                    continue;
                }
                this.speedWarningEtl(gpsData, vehicleInfo);
                this.fatigueDrivingWarningEtl(gpsData, vehicleInfo);
                this.routeWarningEtl(gpsData, vehicleInfo);
                this.timeWarningEtl(gpsData, vehicleInfo);
            } catch (Exception e) {
                LOGGER.error("gps waring etl error ", e);
            }
        }
    }

    @Override
    public void routeWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo) {
        // 遍历每一个GPS实时位置
        // 根据车辆获取其行驶轨迹
        TravelRoute travelRoute = travelRouteMapper.queryTravelRouteByVehicleId(vehicleInfo.getId());
        if (travelRoute == null) {
            travelRoute = travelRouteMapper.queryTravelRouteByVehicleType(vehicleInfo.getVehicleType());
        }
        if (travelRoute != null) {
            // 判断是否按规定路线行驶
            String[] vehicleRouteArray = travelRoute.getVehicleRoute().split(";");
            ArrayList<Point2D.Double> points = new ArrayList<>();
            for (String latitudeLongitude : vehicleRouteArray) {
                String[] latitudeLongitudeTemp = latitudeLongitude.split(",");
                Point2D.Double point1 = new Point2D.Double(Double.parseDouble(latitudeLongitudeTemp[0]), Double.parseDouble(latitudeLongitudeTemp[1]));
                points.add(point1);
            }
            GeneralPath g = genGeneralPath(points);
            Point2D.Double point = new Point2D.Double(gpsData.getLongitude(), gpsData.getLatitude());
            if (g.contains(point)) {
                // 预警写入预警信息表和处置流程表
                addEarlyWarningInfo(gpsData, "1", vehicleInfo,travelRoute.getId());
            }
        } else {
            LOGGER.error("travelRoute is null,vehicleId is :" + vehicleInfo.getId());
        }
    }

    @Override
    public void speedWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo) {
        // 根据车辆获取其速度阀值
        Speed speed = speedMapper.querySpeedByVehicleId(vehicleInfo.getId());
        if (speed == null) {
            speed = speedMapper.querySpeedByVehicleType(vehicleInfo.getVehicleType());
        }
        if (speed != null) {
            // 判断是否超速行驶
            if (gpsData.getSpeed() > speed.getHighSpeed()) {
                // 预警写入预警信息表
                addEarlyWarningInfo(gpsData, "3", vehicleInfo, speed.getId());
            }
        } else {
            LOGGER.error("speed is null,vehicleId is :" + vehicleInfo.getId());
        }
    }

    @Override
    public void timeWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo) {
        // 遍历每一个GPS实时位置
        // 获取车辆信息
        // 根据车辆获取其行驶轨迹
        TravelTime travelTime = travelTimeMapper.queryTravelTimeByVehicleId(vehicleInfo.getId());
        if (travelTime == null) {
            travelTime = travelTimeMapper.queryTravelTimeByVehicleType(vehicleInfo.getVehicleType());
        }
        if (travelTime != null) {
            // 判断是否违规时间行驶
            // 判断日期是否违规
            boolean bDate = false;
            String dateDate = DateUtils.formatDateToString(gpsData.getReportTime(), "yyyy-MM-dd 00:00:00");
            Date dDate = null;
            try {
                dDate = DateUtils.formatDate(dateDate, "yyyy-MM-dd 00:00:00");
            } catch (ParseException e) {
                LOGGER.error("timeWarningEtl formatDate is error:" + e);
            }
            if (dDate.compareTo(travelTime.getDriveStartDate()) >= 0 && dDate.compareTo(travelTime.getDriveEndDate()) <= 0) {
                bDate = true;
            }
            // 判断时间是否违规
            boolean bTime = false;
            String dateTime = DateUtils.formatDateToString(gpsData.getReportTime(), "1970-01-01 HH:mm:ss");
            Date dTime = null;
            try {
                dTime = DateUtils.formatDate(dateTime, "1970-01-01 HH:mm:ss");
            } catch (ParseException e) {
                LOGGER.error("timeWarningEtl formatDate is error:" + e);
            }
            if (dTime.compareTo(travelTime.getDriveStartTime()) >= 0 && dTime.compareTo(travelTime.getDriveEndTime()) <= 0) {
                bTime = true;
            }
            if (bDate || bTime) {
                // 预警写入预警信息表
                addEarlyWarningInfo(gpsData, "2", vehicleInfo, travelTime.getId());
            }
        } else {
            LOGGER.error("travelTime is null,vehicleId is :" + vehicleInfo.getId());
        }
    }

    @Override
    public void fatigueDrivingWarningEtl(GPSData gpsData, VehicleInfo vehicleInfo) {
        // 获取判断疲劳驾驶时间阀值
        FatigueDriving fatigueDriving = fatigueDrivingMapper.queryFatigueDrivingByVehicleId(vehicleInfo.getId());
        if (fatigueDriving == null) {
            fatigueDriving = fatigueDrivingMapper.queryFatigueDrivingByVehicleType(vehicleInfo.getVehicleType());
        }
        if (fatigueDriving != null) {
            // 获取当前车辆连续行驶时间
            long drivingTime = getdrivingTime(gpsData);
            // 判断是否疲劳驾驶
            if (drivingTime >= fatigueDriving.getDrivingTime()) {
                // 预警写入预警信息表
                addEarlyWarningInfo(gpsData, "4", vehicleInfo, fatigueDriving.getId());
            }
        }
    }

    /**
     * 获取当前车辆连续行驶时间
     *
     * @param gpsData
     * @return long
     */
    private long getdrivingTime(GPSData gpsData) {
        // 实时表中查询车辆连续行驶时间
        long drivingTime = 0;
        // 查询实时GPS位置中速度为0的数据
        List<RealTimeData> realTimeDataList = realTimeDataMapper.queryRealTimeDataForSpeed0(gpsData);
        // 获取第一条速度为0的数据的时间减去当前时间为连续行驶时间
        if (realTimeDataList != null && realTimeDataList.size() > 0) {
            RealTimeData realTimeData = realTimeDataList.get(0);
            drivingTime = gpsData.getReportTime().getTime() - realTimeData.getReportTime().getTime();
            return drivingTime / 3600000;
        }
        // 若今日无速度为0的数据，则到历史表中查询车辆连续行驶时间
        // 查询历史GPS位置中速度为0的数据
        List<HistData> histDataList = histDataMapper.queryHistDataForSpeed0(gpsData);
        // 获取第一条速度为0的数据的时间减去当前时间为连续行驶时间
        if (histDataList != null && histDataList.size() > 0) {
            HistData histData = histDataList.get(0);
            drivingTime = gpsData.getReportTime().getTime() - histData.getReportTime().getTime();
        }
        return drivingTime / 3600000;
    }

    /**
     * 预警写入预警信息表
     *
     * @param gpsData
     * @param warningType
     */
    private void addEarlyWarningInfo(GPSData gpsData, String warningType, VehicleInfo vehicleInfo,String warningSetId) {
        // 查询预警信息表中重复预警数据
        EarlyWarningInfo earlyWarningInfo = earlyWarningInfoMapper.queryEarlyWarningInfoByVehicleIdAndWarningType(vehicleInfo.getId(), warningType);
        if (earlyWarningInfo != null) {
            // 通过时间间隔1分钟查询是否为重复预警，若为重复预警则更新预警结束时间和位置
            long timeDifference = gpsData.getReportTime().getTime() - earlyWarningInfo.getWarningEndTime().getTime();
            if (timeDifference / 3600000 * 60 < 1) {
                earlyWarningInfo.setWarningEndTime(gpsData.getReportTime());
                earlyWarningInfo.setWarningEndLocation(gpsData.getLongitude() + "," + gpsData.getLatitude());
                earlyWarningInfoMapper.updateEarlyWarningInfo(earlyWarningInfo);

            } else {
                earlyWarningInfo = buildEarlyWarningInfo(gpsData, vehicleInfo, warningType, warningSetId);
                earlyWarningInfoMapper.addEarlyWarningInfo(earlyWarningInfo);
                DisposalProcess disposalProcess = buildDisposalProcess(earlyWarningInfo);
                disposalProcessMapper.addDisposalProcess(disposalProcess);
            }
        } else {
            earlyWarningInfo = buildEarlyWarningInfo(gpsData, vehicleInfo, warningType, warningSetId);
            earlyWarningInfoMapper.addEarlyWarningInfo(earlyWarningInfo);
            DisposalProcess disposalProcess = buildDisposalProcess(earlyWarningInfo);
            disposalProcessMapper.addDisposalProcess(disposalProcess);
        }
    }

    /**
     * 构建预警信息实体类
     *
     * @return
     */
    private DisposalProcess buildDisposalProcess(EarlyWarningInfo earlyWarningInfo) {
        DisposalProcess disposalProcess = new DisposalProcess();
        disposalProcess.setId(SystemUtils.createUuid());
        disposalProcess.setWarningId(earlyWarningInfo.getId());
        disposalProcess.setJgDepartment("1");
        disposalProcess.setState(0);
        disposalProcess.setJgCcState(0);
        return disposalProcess;
    }

    /**
     * 构建预警信息实体类
     *
     * @param gpsData
     * @param vehicleInfo
     * @param warningType
     * @return
     */
    private EarlyWarningInfo buildEarlyWarningInfo(GPSData gpsData, VehicleInfo vehicleInfo, String warningType ,String warningSetId) {
        EarlyWarningInfo earlyWarningInfo = new EarlyWarningInfo();
        earlyWarningInfo.setId(SystemUtils.createUuid());
        earlyWarningInfo.setVehicleId(vehicleInfo.getId());
        earlyWarningInfo.setWarningType(warningType);
        earlyWarningInfo.setWarningStartTime(gpsData.getReportTime());
        earlyWarningInfo.setWarningStartLocation(gpsData.getLongitude() + "," + gpsData.getLatitude());
        earlyWarningInfo.setWarningEndTime(gpsData.getReportTime());
        earlyWarningInfo.setWarningEndLocation(gpsData.getLongitude() + "," + gpsData.getLatitude());
        earlyWarningInfo.setState(0);
        earlyWarningInfo.setSpeed(gpsData.getSpeed());
        earlyWarningInfo.setWarningSetId(warningSetId);
        return earlyWarningInfo;
    }

    /**
     * 将经纬度点集合转换为GeneralPath对象
     *
     * @param points points 点集合(有序)
     * @return GeneralPath对象
     */
    private GeneralPath genGeneralPath(ArrayList<Point2D.Double> points) {
        GeneralPath path = new GeneralPath();
        if (points.size() < 3) {
            return null;
        }
        path.moveTo((float) points.get(0).getX(), (float) points.get(0).getY());

        for (Iterator<Point2D.Double> it = points.iterator(); it.hasNext();) {
            Point2D.Double point = (Point2D.Double) it.next();
            path.lineTo((float) point.getX(), (float) point.getY());
        }
        path.closePath();
        return path;
    }

    @Override
    public long getContinueDrivingTime(GPSData data) {
        return this.getdrivingTime(data);
    }

}
