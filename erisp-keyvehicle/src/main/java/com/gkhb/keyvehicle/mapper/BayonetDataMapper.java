package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.BayonetData;


/**
 *	卡口
 *	@author weidongping
 *	@createTime 2017年9月7日 上午10:27:02
 */
@Repository
public interface BayonetDataMapper {
	
	/**
	 * 添加卡口位置
	 * @param bayonetdata
	 */
	public void addBayonetData(BayonetData  bayonetdata );

	
	/**
	 * 查询所有代码卡口位置
	 * @return
	 */
	public List<BayonetData> queryBayonetData();

	/**
	 * 修改卡口位置
	 * @param bayonetdata
	 * @return
	 */
	public int updateBayonetData(BayonetData  bayonetdata);
	

}
