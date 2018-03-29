package com.gkhb.keyvehicle.model;

import java.util.Date;

/**
 * <p>Title: WarningRule
 * <p>Description: 预警规则
 * <p>Copyright: Copyright (c) 2017
 *
 * @author wxt.touxin
 * @version 2017/11/1
 */
public class WarningRule {

    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 车辆类型
     */
    private String vehicleType;
    /**
     * 入城证
     */
    private String cityCard;

    /**
     * 车牌类型
     */
    private String vehiclePlateType;

    /**
     * 规则类型 {@link Type}
     */
    private Type ruleType;

    /**
     * 规则详情。JSONArray字符串。实体见 {@link SpeedWarningRule}
     */
    private String rule;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehiclePlateType() {
        return vehiclePlateType;
    }

    public void setVehiclePlateType(String vehiclePlateType) {
        this.vehiclePlateType = vehiclePlateType;
    }

    public Type getRuleType() {
        return ruleType;
    }

    public void setRuleType(Type ruleType) {
        this.ruleType = ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = Type.valueOf(ruleType);
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getCityCard() {
		return cityCard;
	}

	public void setCityCard(String cityCard) {
		this.cityCard = cityCard;
	}


	public enum Type {
        SPEED("速度"),TIME_AREA("时间区域"), TIRED("疲劳"), BUS_FOCUS("车辆聚集");

        private String type;

        Type (String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
