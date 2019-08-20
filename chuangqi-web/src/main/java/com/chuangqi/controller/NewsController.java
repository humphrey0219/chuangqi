/**
 * 
 */
package com.chuangqi.controller;

import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.RequestBean;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.service.NewsService;
import com.chuangqi.vo.NewsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

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
			List<NewsVo> newsVos=paginer.getEntityList();
			if(newsVos!=null&&!newsVos.isEmpty()){
				for (NewsVo newsVo:newsVos) {
					newsVo.setShowImgUrl(showResourceDomainUrl(newsVo.getImgUrl()));
				}
			}
			outPage(paginer);
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("查询订单列表错误", e);
		}
	}

}
