package com.gkhb.keyvehicle.model;
/**
 * 车辆速度表
 * @author WeiGuo
 * @data 2017年9月23日下午3:40:21
 */
public class Speed {
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
     * 最低速度
     */
    private int lowSpeed;
    
    /**
     * 最高速度
     */
    private int highSpeed;
    
    /**
     * 状态
     */
    private int state;
    
    /**
     * 说明
     */
    private String remark;
    
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
    public int getLowSpeed() {
        return lowSpeed;
    }
    public void setLowSpeed(int lowSpeed) {
        this.lowSpeed = lowSpeed;
    }
    public int getHighSpeed() {
        return highSpeed;
    }
    public void setHighSpeed(int highSpeed) {
        this.highSpeed = highSpeed;
        this.remark = "设置限速为小于" + highSpeed + "km/h";
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
