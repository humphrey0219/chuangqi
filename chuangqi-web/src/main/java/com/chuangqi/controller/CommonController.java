/**
 * 
 */
package com.chuangqi.controller;

import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.constant.Constant;
import com.chuangqi.common.utils.RandomCodeUtil;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.vo.SysAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共控制类，不进行拦截登录
 */
@Slf4j
@RestController
public class CommonController extends BaseController{

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
