package com.gkhb.keyvehicle.model.view;

import com.gkhb.keyvehicle.model.DisposalProcess;
import com.gkhb.keyvehicle.model.EarlyWarningInfo;
import com.gkhb.keyvehicle.model.VehicleInfo;

/*
 * 预警处置展示实体类
 * 
 * @author 张顺江
 * @createTime 2017年9月11日 上午12:20:23
 */
public class WarningDisposalView {

    /**
     * 预警处置信息
     */
    private DisposalProcess disposalProcess;

    /**
     * 预警信息
     */
    private EarlyWarningInfo earlyWarningInfo;

    /**
     * 车辆信息
     */
    private VehicleInfo vehicleInfo;
    
    /**
     * 车辆信息
     */
    private Object warningSet;
	/**
	 * 事故次数
	 */
	private int accidentTotal;
	/**
	 * 违法次数
	 */
	private int illegalTotal;

    public DisposalProcess getDisposalProcess() {
        return disposalProcess;
    }

    public void setDisposalProcess(DisposalProcess disposalProcess) {
        this.disposalProcess = disposalProcess;
    }

    public EarlyWarningInfo getEarlyWarningInfo() {
        return earlyWarningInfo;
    }

    public void setEarlyWarningInfo(EarlyWarningInfo earlyWarningInfo) {
        this.earlyWarningInfo = earlyWarningInfo;
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public Object getWarningSet() {
        return warningSet;
    }

    public void setWarningSet(Object warningSet) {
        this.warningSet = warningSet;
    }

	public int getAccidentTotal() {
		return accidentTotal;
	}

	public void setAccidentTotal(int accidentTotal) {
		this.accidentTotal = accidentTotal;
	}

	public int getIllegalTotal() {
		return illegalTotal;
	}

	public void setIllegalTotal(int illegalTotal) {
		this.illegalTotal = illegalTotal;
	}

}
