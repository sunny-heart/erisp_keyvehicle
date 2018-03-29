package com.gkhb.keyvehicle.slave.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.Auth;
import com.gkhb.keyvehicle.model.Resource;


/**
 *	权限信息Mapper
 *	@author chenxiaojie
 *	@createTime 2017年10月25日 上午10:14:02
 */
@Repository
public interface AuthMapper {
	
	/**
	 * 根据id查询用户名称
	 * @param id
	 * @return
	 */
	Auth queryUsernameById(String id);
	/**
	 *  根据id查询分局名称
	 * @param id
	 * @return
	 */
	String queryDepNameById(String id);
	/**
	 * 根据id查询大队名称
	 * @param id
	 * @return
	 */
	String queryGroupNameById(String id);
	/**
	 * 根据角色id查询模块ID 
	 * @param id
	 * @return
	 */
	List<String> queryResourceIdById(String id);
	/**
	 * 根据模块id查询模块名称 
	 * @param id
	 * @return
	 */
	List<Resource> queryResourceNameById(List<String> resourceId);

}
