package com.chuangqi.config.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.utils.AjaxUtils;

/**
 * 全集异常处理
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {
    /**
     * 系统异常处理，比如：404,500
     */
    @ExceptionHandler(value = Throwable.class)
    public void defaultErrorHandler(Throwable e,HttpServletRequest req,HttpServletResponse response) throws Exception {
    	log.error("系统请求异常:{}", e);
    	String msg=e.getLocalizedMessage();
    	
    	if("XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"))){
    		ResultCode resultCode=new ResultCode(ResultCode.CODE_9999, ResultCode.MSG_9999);
    		if(msg.contains("BeanPropertyBindingResult")){
        		resultCode.setMsg("操作失败,请检查输入参数");
        	}
    		AjaxUtils.respWrite(resultCode,response);
    	}else{
    		response.sendRedirect(req.getContextPath()+"/exce.htm"); 
    	}
    }
}
