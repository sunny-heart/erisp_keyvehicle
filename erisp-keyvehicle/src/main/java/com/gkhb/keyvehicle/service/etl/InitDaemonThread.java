package com.gkhb.keyvehicle.service.etl;

/**
 * 初始化守护线程
 * @author 张顺江
 * @createTime 2016年4月21日 上午11:46:33
 */
public interface InitDaemonThread {

	void startDaemonThread(Runnable runnable,String thread);
}
