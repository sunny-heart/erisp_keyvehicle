package com.gkhb.keyvehicle.common.datasource;

import org.aspectj.lang.JoinPoint;

public class DataSourceAspect {

	/**
     * @param point
     */
    public void before(JoinPoint point) {
        //获取目标对象的类类型
        Class<?> target = point.getTarget().getClass();
        Class<?>[] clazzes = target.getInterfaces();
        Class<?> clazz = null;
        if(clazzes.length>0){
        	clazz = clazzes[0];
        }
        //获取mapper所在包名用于区分不同数据源
        String whichDataSource = clazz.getPackage().getName().substring(20, clazz.getPackage().getName().lastIndexOf("."));
        if ("slave".equals(whichDataSource)) {
        	DynamicDataSourceHolder.setDataSource(Constants.DATASOURCE_SLAVE);
        } else {
        	DynamicDataSourceHolder.setDataSource(Constants.DATASOURCE_MASTER);
        }
    }

    /**
     * 执行后将数据源置为空
     */
    public void after() {
    	DynamicDataSourceHolder.setDataSource(null);
    }
}