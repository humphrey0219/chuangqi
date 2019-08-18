/**
 * 
 */
package com.chuangqi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.constant.Constant;
import com.chuangqi.common.utils.RandomCodeUtil;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.vo.SysAccountVo;

/**
 * 公共控制类，不进行拦截登录
 */
@Slf4j
@RestController
public class CommonController extends BaseController{
	@Autowired
	private SysAccountService sysAccountService;
	
	@RequestMapping("/common/loginUI")
	public ModelAndView loginUI(){
		return new ModelAndView("loginUI");
	}
	
	@RequestMapping("/common/login")
	public void login(SysAccountVo sysAccountVo){
		ResultCode resultCode=new ResultCode();
		try{
			String code=getRequest().getParameter("code");
			if(StringUtils.isBlank(code)){
				resultCode.setFail("请输入验证码");
				return ;
			}
			String sessoinCode=(String)getRequest().getSession().getAttribute(RandomCodeUtil.RD_CODE);
			if(StringUtils.isBlank(sessoinCode)){
				resultCode.setFail("验证码已过期,请重新获取");
				return ;
			}
			if(!StringUtils.equalsIgnoreCase(code, sessoinCode)){
				resultCode.setFail("输入验证码错误");
				return ;
			}
			SysAccountVo vo=new SysAccountVo();
			vo.setUserName(sysAccountVo.getUserName());
			vo.setPwd(sysAccountVo.getPwd());
			vo=sysAccountService.get(vo);
			if(vo!=null&&vo.getId()>0){ 
				if(Constant.STATUS_0.equals(vo.getStatus())){
					resultCode.setFail("您的账户已被禁用，请联系管理员");
					return ;
				}
				resultCode.setSuccess("登录成功");
				getRequest().getSession().removeAttribute(RandomCodeUtil.RD_CODE);
				getRequest().getSession().setAttribute(Constant.SESSION_LOGIN_USER, vo);
			}else{
				resultCode.setFail("账号密码错误");
			}
		}catch (Throwable e) {
			log.error("登录异常，sysAccount={},异常信息={}",sysAccountVo,e);
			resultCode.setFail("登录异常");
		}finally{
			resWriteObjectJson(resultCode);
		}
	}
	
	@RequestMapping("/common/loginOut")
	public void loginOut(SysAccountVo sysAccountVo){
		ResultCode resultCode=ResultCode.newSuccess();
		getRequest().getSession().removeAttribute(Constant.SESSION_LOGIN_USER);
		resWriteObjectJson(resultCode);
	}
	
	 /**
     * 随机验证码
     */
    @RequestMapping("/common/vcode")
    public void vcode(HttpServletRequest request,HttpServletResponse response)
    {
        try {
             RandomCodeUtil rdnu=RandomCodeUtil.getInstance().createData();  
             response.getOutputStream().write(rdnu.getImageBytes());//取得带有随机字符串的图片  
             String str = rdnu.getString().toString();
             request.getSession().setAttribute(RandomCodeUtil.RD_CODE, str);//取得随机字符串放入HttpSession
             log.info("生成随机验证码={}",str);
        } catch (Exception e) {
            e.fillInStackTrace();
            log.error("随机获取验证码错误", e);
        }
    }
	
	//登录后管理页面
	@RequestMapping("/index")
	public ModelAndView index(){
		modelAndView("index/index");
		return modelAndView;
	}
	
}
