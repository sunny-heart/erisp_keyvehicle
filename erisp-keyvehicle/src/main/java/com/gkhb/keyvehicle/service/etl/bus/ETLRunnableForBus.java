package com.gkhb.keyvehicle.service.etl.bus;

/**
 * 公交车数据获取
 * 
 * @author eddy
 */
public class ETLRunnableForBus implements Runnable {

	private BusEtlService busEtlService;

    public ETLRunnableForBus(BusEtlService busEtlService) {
        this.busEtlService = busEtlService;
    }
	
    @Override
    public void run() {
    	busEtlService.BusEtl();
    }

}
