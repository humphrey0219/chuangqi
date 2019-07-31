/**
 * 
 */
package com.chuangqi.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.constant.Constant;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.vo.SysAccountVo;

/**
 * 公共控制类，不进行拦截登录
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController{
	@Autowired
	private SysAccountService sysAccountService;
	
	@RequestMapping("/loginUI")
	public ModelAndView loginUI(){
		modelAndView("login");
		return modelAndView;
	}
	
	@RequestMapping("/login")
	public void login(SysAccountVo sysAccountVo){
		ResultCode resultCode=new ResultCode();
		try{
			SysAccountVo vo=new SysAccountVo();
			vo.setUserName(sysAccountVo.getUserName());
			vo.setPwd(sysAccountVo.getPwd());
			vo=sysAccountService.get(vo);
			if(vo!=null&&vo.getId()>0){
				resultCode.setSuccess("登录成功");
				getRequest().getSession().setAttribute(Constant.SESSION_LOGIN_USER, vo);
			}else{
				resultCode.setFail("账号密码错误");
			}
		}catch (Throwable e) {
			log.error("登录异常，sysAccount={},异常信息={}",sysAccountVo,e);
			resultCode.setFail("登录异常");
		}
		resWriteObjectJson(resultCode);
	}
	
	@RequestMapping("/loginOut")
	public void loginOut(SysAccountVo sysAccountVo){
		ResultCode resultCode=ResultCode.newSuccess();
		getRequest().getSession().removeAttribute(Constant.SESSION_LOGIN_USER);
		resWriteObjectJson(resultCode);
	}
}
