package com.gkhb.keyvehicle.slave.service;

import java.util.List;

import com.gkhb.keyvehicle.model.Auth;
import com.gkhb.keyvehicle.model.Resource;

/**
 *	权限信息服务
 *	@author chenxiaojie
 *	@createTime 2017年10月25日 上午10:20:08
 */
public interface AuthService {
	
	/**
	 * 根据id查询用户名称
	 * @param id
	 * @return
	 */
	public Auth queryUsernameById(String id);
	
	/**
	 *  根据id查询分局名称
	 * @param id
	 * @return
	 */
	public String queryDepNameById(String id);
	
	/**
	 * 根据id查询大队名称
	 * @param id
	 * @return
	 */
	public String queryGroupNameById(String id);
	
	/**
	 * 根据角色id查询模块ID 
	 * @param id
	 * @return
	 */
	public List<String> queryResourceIdById(String id);
	
	/**
	 * 根据模块id查询模块名称 
	 * @param id
	 * @return
	 */
	public List<Resource> queryResourceNameById(List<String> resourceId);
	
	/**
	 * 通过角色id获取模块名称
	 * @param roleId
	 * @return
	 */
	public List<Resource> getResourceName(String roleId);

}
