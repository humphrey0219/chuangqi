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
	


}
