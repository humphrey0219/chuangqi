/**
 * 
 */
package com.chuangqi.controller;

import com.chuangqi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制类
 * @author wmk
 *
 */
@Slf4j
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController extends BaseController{
	

	//上转新闻图片目录
	@Value("${upload.file.news.imgs.dir}")
	protected String  uploadFileNewsImgsDir;
	
	/**
	 * 图片编辑器Html上传
	 * @param file
	 */
	@RequestMapping("/newsImgFile")
	public void newsImgFile(@RequestParam(value = "imgFile", required = true) MultipartFile file){
		Map<String, Object> rtMap=new HashMap<String, Object>();
		try {
			//自定义目录，如果没有就用新闻图片目录
			String dir=getRequest().getParameter("dir");
			if(StringUtils.isNotBlank(dir)){
				uploadFileNewsImgsDir=dir;
			}
			
			//动态目录
			String dyDir="/"+DateUtils.getFormatNowDate(DateUtils.YYYY_MM);
			dyDir=uploadFileNewsImgsDir+dyDir;
			File fileObj=new File(uploadFileBaseDir+dyDir);
    		if(!fileObj.exists()){//不存在目录就创建
    			fileObj.mkdirs();
    		}
    		
			//设置文件名
    		String fileName=file.getOriginalFilename().toLowerCase();
    		fileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length());

    		//存储数据库路径
    		String dbPath=dyDir+"/"+fileName;
    		
    		fileObj=new File(uploadFileBaseDir+dbPath);
    		file.transferTo(fileObj);//上传文件到该目录下面
    		
    		//上传成功，返回url
    		rtMap.put("error", 0);
    		rtMap.put("url", showResourceDomainUrl(dbPath));
    		rtMap.put("dbPath",dbPath);
			
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("上传新闻图片异常", e);
			
			rtMap.put("error", 1);
			rtMap.put("message", "上传新闻图片异常");
		}
		resWriteObjectJson(rtMap);
	}
}
