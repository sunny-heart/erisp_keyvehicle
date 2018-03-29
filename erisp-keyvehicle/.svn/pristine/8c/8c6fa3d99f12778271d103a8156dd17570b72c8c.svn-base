package com.gkhb.keyvehicle.service.etl;

import org.springframework.stereotype.Service;

@Service("initDaemonThreadService")
public class InitDaemonThreadService implements InitDaemonThread {

	public void startDaemonThread(Runnable runnable,String threadName) {
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.setName(threadName);
		thread.start();
	}

}
