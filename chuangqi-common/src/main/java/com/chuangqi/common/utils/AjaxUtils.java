/**
 * 
 */
package com.chuangqi.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * 工具类
 * @author wmk
 *
 */
public class AjaxUtils {
	/**
	 * json响应数据
	 * @param obj
	 * @throws IOException
	 */
	public static void respWrite(Object obj,HttpServletResponse  response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter printWriter= response.getWriter();
		printWriter.print(JSONObject.toJSONString(obj));
	}
}
