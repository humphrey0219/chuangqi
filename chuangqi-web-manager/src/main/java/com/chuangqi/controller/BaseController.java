package com.chuangqi.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.SearchRules;

/**
 * 基础控制类，所有基础该类
 * @author weminke
 */
@Slf4j
public class BaseController {
	
	protected static final String UTF_8="UTF-8";
	protected static final String CONTENTTYPE_JSON="text/x-json;charset=UTF-8";
	
	//上转文件根路径
	//@Value("${excel.file.uploads.basedir}")
	protected String  excelFileUploadsBasedir;

	protected ModelAndView modelAndView;
	protected ModelAndView modelAndView(String pageUrl) {
		modelAndView=new ModelAndView(pageUrl);
		return modelAndView;
	}
	
	
	@SuppressWarnings("rawtypes")
	protected Paginer getPaginer() {
		HttpServletRequest request=getRequest();
		String pageIdStr=request.getParameter("page");
		String pageSizeStr=request.getParameter("pagesize");
		
		int pageId=1,pageSize=10;
		if(StringUtils.isNotBlank(pageIdStr)){
			pageId=Integer.parseInt(pageIdStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize=Integer.parseInt(pageSizeStr);
		}
		return new Paginer<>(pageId, pageSize);
	}
	
	protected void outPage(Paginer<?> paginer) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("Rows",paginer.getEntityList());
		dataMap.put("Total", paginer.getCount());
		String json=JSONObject.toJSONString(dataMap);
		resWriteJson(json);
	}
	
	protected void resWriteObjectJson(Object obj) {
		String json=JSONObject.toJSONString(obj);
		resWriteJson(json);
	}
	
	protected void resWriteJson(String json) {
		try{
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			response.setCharacterEncoding(UTF_8);
			response.setContentType(CONTENTTYPE_JSON);
			PrintWriter writer = response.getWriter();
			writer.print(json);
			writer.flush();
			writer.close();
		}catch (Throwable e) {
			log.error("响应json数据错误,json={},异常信息={}", json,e);
		}
	}
	
	protected String getSearchRules() {
		HttpServletRequest request=getRequest();
		String searchData=request.getParameter("searchData");
		SearchRules searchRules=JSONObject.parseObject(searchData, SearchRules.class);
		if(searchRules==null){
			return "";
		}
		return searchRules.getRulesSql();
	}
	
	/**
	 * 导出excel
	 */
	protected void export(HSSFWorkbook wb, String fileName) throws IOException {
		// 设置response的编码方式
		HttpServletResponse response=getResponse();
		response.setContentType("application/x-msdownload");
		// 解决中文乱码
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
		OutputStream output = response.getOutputStream();
		wb.write(output);
		output.flush();
		output.close();
	}
	
	/**
	 * @return 获取上转Excel文件路径
	 */
	protected String getExcelFileUploadsBasedir() {
		return excelFileUploadsBasedir;
	}

	protected HttpServletResponse getResponse(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	protected HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
