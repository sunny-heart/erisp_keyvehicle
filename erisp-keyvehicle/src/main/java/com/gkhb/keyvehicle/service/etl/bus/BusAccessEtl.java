package com.gkhb.keyvehicle.service.etl.bus;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公交车数据对接
 * @author eddy
 *
 */
@Service("busAccessEtlService")
public class BusAccessEtl implements BusAccessEtlService {

    @Autowired
    private BusEtlService busEtlService;

    ScheduledThreadPoolExecutor BusScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    @Override
    public void startEtlServer() {
    	//定时获取公交车数据
    	System.out.println("---------------------etl start-------------------------");
    	BusScheduledThreadPoolExecutor.scheduleWithFixedDelay(new ETLRunnableForBus(busEtlService), 1 * 1000, 2 * 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean shutDownEtlServer() {
        if (null != BusScheduledThreadPoolExecutor && !BusScheduledThreadPoolExecutor.isShutdown()) {
        	BusScheduledThreadPoolExecutor.shutdown();
        }
        return true;
    }

}
