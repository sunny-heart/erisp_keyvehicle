package com.gkhb.keyvehicle.slave.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.DevCameraEvent;

/**
 * 卡口信息Mapper
 * @author Administrator
 *
 */
@Repository
public interface DevCameraEventMapper {

	/**
	 * 查询所有卡口信息
	 * @return
	 */
	List<DevCameraEvent> queryAll();
	
	/**
	 * 根据设备id查询卡口
	 * @param deviceId
	 * @return
	 */
	DevCameraEvent queryDevCameraById(String deviceId);
	
}
