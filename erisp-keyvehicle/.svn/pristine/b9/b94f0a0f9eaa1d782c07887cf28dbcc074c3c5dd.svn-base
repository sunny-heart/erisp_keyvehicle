package com.gkhb.keyvehicle.slave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.model.DevCameraEvent;
import com.gkhb.keyvehicle.slave.mapper.DevCameraEventMapper;

@Service
public class DevCameraEventServiceImpl implements DevCameraEventService{
	
	@Autowired
	private DevCameraEventMapper devCameraEventMapper;

	@Override
	public List<DevCameraEvent> queryAll() {
		return devCameraEventMapper.queryAll();
	}

	@Override
	public DevCameraEvent queryDevCameraById(String deviceId) {
		return devCameraEventMapper.queryDevCameraById(deviceId);
	}

	

}
