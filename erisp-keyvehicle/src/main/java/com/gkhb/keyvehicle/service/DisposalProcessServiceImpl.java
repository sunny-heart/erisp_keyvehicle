package com.gkhb.keyvehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gkhb.keyvehicle.mapper.DisposalProcessMapper;
import com.gkhb.keyvehicle.model.view.DisposalProcessView;

/**
 * 预警信息
 * @author eddy
 *
 */
@Service
public class DisposalProcessServiceImpl implements DisposalProcessService {
	
	@Autowired
	private DisposalProcessMapper disposalProcessMapper;

	@Override
	public int updateJgCcState(DisposalProcessView disposalProcessView) {
		return disposalProcessMapper.updateJgCcState(disposalProcessView);
	}
	
}
