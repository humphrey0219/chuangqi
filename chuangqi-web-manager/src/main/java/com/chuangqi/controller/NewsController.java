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
import com.chuangqi.vo.NewsVo;

/**
 * 新闻控制类
 * @author wmk
 *
 */
@Slf4j
@RestController
@RequestMapping("/news")
public class NewsController extends BaseController{
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/newslistUI")
	public ModelAndView newslistUI(@PathParam(value="serviceType") Integer serviceType){
		modelAndView("news/newslistUI");
		this.modelAndView.addObject("serviceType", serviceType);
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/newslist")
	public void newslist(@PathParam(value="serviceType") Integer serviceType){
		try{
			NewsVo vo= new NewsVo();
			vo.setServiceType(serviceType);
			vo.setWhereSql(getSearchRules());
			vo.setOrderBySql(" order by id desc ");
			Paginer<NewsVo> paginer=getPaginer();
			paginer.setObj(vo);
			paginer=newsService.getPaginer(paginer);
			outPage(paginer);
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("查询订单列表错误", e);
		}
	}
	
	//添加数据页面
	@RequestMapping("/addNewsUI")
	public ModelAndView addleaveUI(@PathParam(value="serviceType") Integer serviceType){
		
		return modelAndView("news/addNewsUI").addObject("serviceType", serviceType);
	}
	
	//添加数据
	@RequestMapping("/addNews")
	public void addNews(NewsVo vo){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			Long rt=newsService.add(vo);
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
	@RequestMapping("/updNewsUI")
	public ModelAndView updNewsUI(NewsVo vo){
			vo=newsService.get(vo);
			modelAndView("news/updNewsUI");
			modelAndView.addObject("vo", vo);
			return modelAndView;
	}
		
	//修改数据
	@RequestMapping("/updNews")
	public void updNews(NewsVo vo){
			ResultCode resultCode=ResultCode.newSuccess();
			try{
				Long rt=newsService.updateByUqKey(vo);
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
	@RequestMapping("/delNews")
	public void delNews(RequestBean requestBean){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			String ids=requestBean.getIds();
			NewsVo t=new NewsVo();
			t.setWhereSql(" id in("+ids+")");
			int rt=newsService.del(t);
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
