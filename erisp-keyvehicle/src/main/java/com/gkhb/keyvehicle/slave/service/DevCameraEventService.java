package com.gkhb.keyvehicle.slave.service;

import java.util.List;

import com.gkhb.keyvehicle.model.DevCameraEvent;

/**
 * 卡口信息服务
 * @author Administrator
 *
 */
public interface DevCameraEventService {
	
	/**
	 * 查询所有卡口信息
	 * @return
	 */
	public List<DevCameraEvent> queryAll();
	
	/**
	 * 根据设备id查询卡口信息
	 * @param deviceId
	 * @return
	 */
	public DevCameraEvent queryDevCameraById(String deviceId);
}
