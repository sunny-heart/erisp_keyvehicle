package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.model.AscriptionCompany;
import com.gkhb.keyvehicle.model.param.QueryConditionData;

/**
 * 所属企业Mapper
 * 
 * @author eddy
 */
@Repository
public interface AscriptionCompanyMapper {

	/**
	 * 查询出租车所属企业信息
	 * 
	 * @return
	 */
	List<AscriptionCompany> queryAscriptionCompany(@Param("queryConditionData") QueryConditionData queryConditionData);

	/**
	 * 查询物流车所属企业信息
	 * 
	 * @return
	 */
	List<AscriptionCompany> queryVehicleInfoAscriptionCompany(
			@Param("queryConditionData") QueryConditionData queryConditionData);
}
