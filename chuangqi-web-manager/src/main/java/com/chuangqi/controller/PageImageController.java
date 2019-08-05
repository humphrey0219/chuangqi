/**
 *
 */
package com.chuangqi.controller;

import com.chuangqi.bean.File;
import com.chuangqi.bean.GridData;
import com.chuangqi.service.PageImageService;
import com.chuangqi.service.WebPageService;
import com.chuangqi.vo.PageImageVo;
import com.chuangqi.vo.WebPageVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.ResultCode;

import java.io.IOException;
import java.util.List;

/**
 * 页面图片控制类
 * @author jellyra
 */
@Slf4j
@RestController

public class PageImageController extends BaseController{


    public static  String IMAGE_PAGE = "/Users/jellyra/Box/Service/Java/chuangqi/chuangqi-web-manager/src/main/resource/static/image/";
    @Autowired
    private PageImageService     mService ;



    //图片列表视力
    @RequestMapping("image/list")
    public ModelAndView list() {
        modelAndView("image/list");
        return modelAndView ;
    }

    //创建新图片
    @RequestMapping("image/add")
    public void add(PageImageVo imageVo){
        //log.error("新页面数据，webPageVo={}",pageVo);
        Long newPage = mService.add(imageVo);
        sendOperationResult(newPage.intValue(), "新建图片");


    }

    // 保存图片
    @RequestMapping("image/save")
    public void saveImage(File file){

        MultipartFile f = file.getFile();

        String fileName = f.getName();
        log.error("保存图片 file {} name {}, size {} ",f, f.getOriginalFilename(), f.getSize());


        java.io.File des = new java.io.File(IMAGE_PAGE  + f.getOriginalFilename());

        log.error("图片地址 {}", des.getAbsolutePath());

        try {
            f.transferTo(des);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("保存出错 {}", e);
            sendOperationResult(-1, "保存图片");
        }
    }
    // 新建图片视图
    @RequestMapping("image/addUI")
    public ModelAndView index(){
        modelAndView("image/addUI");
        return modelAndView;
    }

    // 图片数据
    @RequestMapping("image/data")
    public void allPage(){
        List pages = mService.getList(new PageImageVo());
        GridData data = new GridData(pages, pages.size());
        if(pages != null ){
            resWriteObjectJson(data);
        }else{
            ResultCode resultCode =ResultCode.newSuccess();
            resultCode.setFail("数据不可用");
            resWriteObjectJson(resultCode);
        }

    }
    // 修改图片
    @RequestMapping("image/update")
    public void  modify(PageImageVo imageVo){

        Long newPage = mService.updateByUqKey(imageVo);

        sendOperationResult(newPage.intValue(), "更新图片");
    }



}
