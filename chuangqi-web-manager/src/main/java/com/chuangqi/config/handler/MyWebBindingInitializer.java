package com.chuangqi.config.handler;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.chuangqi.common.convert.DatePropertyEditorSupport;

//自定义绑定请求参数
public class MyWebBindingInitializer implements WebBindingInitializer{
	
	public MyWebBindingInitializer() {
	}
	
	@Override
	public void initBinder(WebDataBinder arg0, WebRequest arg1) {
		//自定义日期转换对象
		arg0.registerCustomEditor(Date.class, new DatePropertyEditorSupport());
	}

}
