/**
 * 
 */
package com.chuangqi.controller;

import javax.websocket.server.PathParam;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.RequestBean;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.service.NewsService;
import com.chuangqi.service.SmileTestService;
import com.chuangqi.vo.NewsVo;
import com.chuangqi.vo.SmileTestVo;

/**
 * 微笑测试控制类
 * @author wmk
 *
 */
@Slf4j
@RestController
@RequestMapping("/smileTest")
public class SmileTestController extends BaseController{
	@Autowired
	private SmileTestService smileTestService;
	
	@RequestMapping("/smileTestlistUI")
	public ModelAndView smileTestlistUI(){
		modelAndView("smileTest/smileTestlistUI");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/smileTestlist")
	public void smileTestlist(){
		try{
			SmileTestVo vo= new SmileTestVo();
			vo.setWhereSql(getSearchRules());
			vo.setOrderBySql(" order by id desc ");
			Paginer<SmileTestVo> paginer=getPaginer();
			paginer.setObj(vo);
			paginer=smileTestService.getPaginer(paginer);
			outPage(paginer);
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("查询列表错误", e);
		}
	}
	
	//添加数据页面
	@RequestMapping("/addSmileTestUI")
	public ModelAndView addSmileTestUI(){
		return modelAndView("smileTest/addSmileTestUI");
	}
	
	//添加数据
	@RequestMapping("/addSmileTest")
	public void addSmileTest(SmileTestVo vo){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			Long rt=smileTestService.add(vo);
			if(rt==null||rt<=0){
				resultCode.setFail("添加失败,请联系技术人员");
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("添加异常,data={},异常信息={}",vo, e);
			resultCode.setFail("添加异常,请联系技术人员");
		}
		resWriteObjectJson(resultCode);
	}
	
	//修改数据页面
	@RequestMapping("/updSmileTestUI")
	public ModelAndView updSmileTestUI(SmileTestVo vo){
			vo=smileTestService.get(vo);
			modelAndView("smileTest/updSmileTestUI");
			modelAndView.addObject("vo", vo);
			return modelAndView;
	}
		
	//修改数据
	@RequestMapping("/updSmileTest")
	public void updSmileTest(SmileTestVo vo){
			ResultCode resultCode=ResultCode.newSuccess();
			try{
				Long rt=smileTestService.updateByUqKey(vo);
				if(rt==null||rt<=0){
					resultCode.setFail("修改失败,请联系技术人员");
				}
			}catch (Throwable e) {
				e.printStackTrace();
				log.error("修改异常,data={},异常信息={}",vo, e);
				resultCode.setFail("修改异常,请联系技术人员");
			}
			resWriteObjectJson(resultCode);
	}
	
	//删除数据
	@RequestMapping("/delSmileTest")
	public void delNews(RequestBean requestBean){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			String ids=requestBean.getIds();
			SmileTestVo t=new SmileTestVo();
			t.setWhereSql(" id in("+ids+")");
			int rt=smileTestService.del(t);
			if(rt<=0){
				resultCode.setFail("删除失败,请联系技术人员");
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("删除异常,data={},异常信息={}",requestBean, e);
			resultCode.setFail("删除异常,请联系技术人员");
		}
		resWriteObjectJson(resultCode);
	}
}
