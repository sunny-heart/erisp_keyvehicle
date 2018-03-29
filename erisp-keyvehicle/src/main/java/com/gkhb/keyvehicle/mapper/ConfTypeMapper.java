package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.ConfType;



/**
 *	配置信息表mapper
 *	@author weidongping
 *	@createTime 2017年9月7日 上午10:27:02
 */
@Repository
public interface ConfTypeMapper {
	/**
	 * 添加配置信息
	 * @param conftype
	 */
	public void addConfType(ConfType  conftype);

	
	/**
	 * 查询所有配置信息
	 * @return
	 */
	public List<ConfType> queryConfType();

	/**
	 * 修改配置信息
	 * @param conftype
	 * @return
	 */
	public int updateConfType(ConfType  conftype);
}
