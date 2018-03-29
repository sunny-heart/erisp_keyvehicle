package com.gkhb.keyvehicle.common.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gkhb.keyvehicle.common.model.ResponseMessage;
import com.gkhb.keyvehicle.common.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共Controller类<br>
 * 继承自定义拦截器
 * author JieChen
 * createTime 3/14/16 8:23 PM
 */
@Controller
@ResponseBody
public class BaseController  {

    private Logger logger = LoggerFactory.getLogger("Exception");

    @ExceptionHandler(Exception.class)
    public ResponseMessage handleException(Exception e){
        logger.error("error",e);
        logger.error("exceptionMessage",e.getMessage());
        logger.error("cause",e.getCause());
        Map<String,Object> messageMap = new HashMap<>();
        messageMap.put("exceptionMessage",e.getMessage());
        return new ResponseMessage(Constants.STATE_ERROR_500,messageMap);
    }

    public static void main(String[] args) {

    }
}
