/**
 * 
 */
package com.chuangqi.config.interceptor;

import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.constant.Constant;
import com.chuangqi.common.utils.AjaxUtils;
import com.chuangqi.vo.SysAccountVo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * @author weiminke
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
			/*HttpSession session = request.getSession();
			SysAccountVo accountVo=(SysAccountVo)session.getAttribute(Constant.SESSION_LOGIN_USER);
	        if(accountVo== null){
	        	//判断session中有没有user信息
	            if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
	        		ResultCode resultCode=ResultCode.newSuccess();
	        		resultCode.setFail("操作超时,请重新登录");
	        		AjaxUtils.respWrite(resultCode, response);
	            }else{
	            	response.sendRedirect(request.getContextPath()+"/common/loginUI");     //没有user信息的话进行路由重定向
	            }
	            return false;
	        }
	        //有权限且已登录
	        String uri=request.getRequestURI();
	       
	        //如果账号权限,且不是超级管理，不能操作
	        if(uri.contains("/account")&&!Constant.LEVEL_ROLE_1.equals(accountVo.getLevel())){
	        	response.sendRedirect(request.getContextPath()+"/no_auth.htm");
	        	return false;
	        }*/
	        
	        return true;        //有的话就继续操作
	}

}
