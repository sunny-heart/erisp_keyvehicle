package com.gkhb.keyvehicle.model;

/**
 * 车辆在线状态实体类
 * @author eddy
 *
 */
public class VehicleOnlineState {
	
	/**
	 * 主键id
	 */
	private String id;	
	/**
	 * 车辆ID
	 */
	private String vehicleId;
	/**
	 * 车辆在线状态
	 */
	private int vehicleState;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getVehicleState() {
		return vehicleState;
	}
	public void setVehicleState(int vehicleState) {
		this.vehicleState = vehicleState;
	}
}
