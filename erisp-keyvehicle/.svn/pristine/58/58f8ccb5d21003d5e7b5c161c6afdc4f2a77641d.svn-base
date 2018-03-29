package com.gkhb.keyvehicle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gkhb.keyvehicle.common.model.Page;
import com.gkhb.keyvehicle.model.param.QueryConditionData;
import com.gkhb.keyvehicle.model.view.WarningSetView;

/**
 * 预警设置mapper
 * @author WeiGuo
 * @data 2017年9月23日下午5:15:07
 */
@Repository
public interface WarningSetMapper {
	
     /**
      * 添加超速预警
     * @param warningData
     * @return 
     */
    public int addWarningOverSpeed(WarningSetView warningData);
    
    
    /**
     * 添加违规时间预警
     * @param warningData 
     */
    public int addWarningWrongTime(WarningSetView warningData);
    
    /**
     * 添加违规线路预警
     * @param warningData 
     */
    public int addWarningWrongRoute(WarningSetView warningData);
    

    /**
     * 添加疲劳驾驶预警
     * @param warningData 
     */
    public int addWarningFatigue(WarningSetView warningData);
    
    /**
     * 查询预警信息
     * @return
     */
    public List<WarningSetView> queryWarningInfo(@Param("queryConditionData")QueryConditionData queryConditionData,Page page);
    
    
    /**
     * 修改超速预警
    * @param warningData
    * @return 
    */
   public int updateWarningOverSpeed(WarningSetView warningData);
   
   
   /**
    * 修改违规时间预警
    * @param warningData 
    */
   public int updateWarningWrongTime(WarningSetView warningData);
   
   /**
    * 修改违规线路预警
    * @param warningData 
    */
   public int updateWarningWrongRoute(WarningSetView warningData);
   

   /**
    * 修改疲劳驾驶预警
    * @param warningData 
    */
   public int updateWarningFatigue(WarningSetView warningData);
   
    
}
