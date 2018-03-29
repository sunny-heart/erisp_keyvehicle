package com.gkhb.keyvehicle.model;
/**
 * 驾驶时间表
 * @author WeiGuo
 * @data 2017年9月23日下午3:40:21
 */
public class FatigueDriving {
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 车辆类型
     */
    private String vehicleType;
    /**
     * 车辆id
     */
    private String vehicleId;
    
    /**
     * 驾驶时间
     */
    private int drivingTime;
    
    /**
     * 状态
     */
    private int state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(int drivingTime) {
        this.drivingTime = drivingTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
