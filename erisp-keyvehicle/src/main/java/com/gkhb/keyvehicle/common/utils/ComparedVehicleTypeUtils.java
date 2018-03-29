package com.gkhb.keyvehicle.common.utils;
/**
 *	判断车辆类型工具类
 *	@author chenxiaojie
 *	@createTime 2017年9月12日 下午6:05:56
 */
public final class ComparedVehicleTypeUtils {

	public ComparedVehicleTypeUtils() {
		
	}
	
	/**
	 * 对比车辆类型返回车辆类型字符串
	 * @param vehicleType 车辆类型
	 * @return
	 */
	public static String comparedVehicleType(String vehicleType){
		String result = null;
		switch (vehicleType) {
		case "A":
			result = "新能源";
			break;
		case "B":
			result = "冷链";
			break;
		case "C":
			result = "危化品";
			break;
		case "D":
			result = "普通";
			break;
		default:
			result = "";
			break;
		}
		return result;
	}
	
	/**
	 * 对比子车辆类型返回子车辆类型字符串
	 * @param vehicleType	车辆子类型
	 * @return
	 */
	public static String comparedChildVehicleType(String vehicleType){
		String result = null;
		switch (vehicleType) {
		case "A":
			result = "新能源";
			break;
		case "B":
			result = "冷链";
			break;
		case "C":
			result = "危化品";
			break;
		case "D":
			result = "普通";
			break;
		default:
			result = "";
			break;
		}
		return result;
	}
}
