package com.gkhb.keyvehicle.model;

import java.util.List;

/**
 *	权限信息实体类
 *	@author chenxiaojie
 *	@createTime 2017年10月25日 上午9:10:27
 */
public class Auth {
	
	/**
	 * 分局ID
	 */
	private String depId;
	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 大队ID
	 */
	private String groupId;
	/**
	 * 用户名称
	 */
	private String fstrName;
	/**
	 * 分局名称
	 */
	private String depName;
	/**
	 * 大队名称
	 */
	private String groupName;
	/**
	 * 模块名称
	 */
	private List<Resource> resourceName;
	/**
	 * 用户描述
	 */
	private String fstrDesc;
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public List<Resource> getResourceName() {
		return resourceName;
	}
	public void setResourceName(List<Resource> resourceName) {
		this.resourceName = resourceName;
	}
	public String getFstrName() {
		return fstrName;
	}
	public void setFstrName(String fstrName) {
		this.fstrName = fstrName;
	}
	public String getFstrDesc() {
		return fstrDesc;
	}
	public void setFstrDesc(String fstrDesc) {
		this.fstrDesc = fstrDesc;
	}
	
	
	

}
