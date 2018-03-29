package com.gkhb.keyvehicle.model;

/**
 *	配置类型
 *	@author weidongping
 *	@createTime 2017年9月7日 上午10:27:02
 */
public class ConfType {
	
	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 编码
	 */
	private String key;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 全名
	 */
	
	private String full_name;
	
	/**
	 * 状态
	 */
	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	

	
	
}
