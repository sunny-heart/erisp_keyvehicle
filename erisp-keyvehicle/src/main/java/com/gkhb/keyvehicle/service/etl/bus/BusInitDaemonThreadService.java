package com.gkhb.keyvehicle.service.etl.bus;

import org.springframework.stereotype.Service;

@Service("busInitDaemonThreadService")
public class BusInitDaemonThreadService implements BusInitDaemonThread {

	public void startDaemonThread(Runnable runnable,String threadName) {
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.setName(threadName);
		thread.start();
	}

}
