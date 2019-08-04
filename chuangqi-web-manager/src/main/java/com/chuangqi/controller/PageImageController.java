/**
 *
 */
package com.chuangqi.controller;

import com.chuangqi.bean.GridData;
import com.chuangqi.service.PageImageService;
import com.chuangqi.service.WebPageService;
import com.chuangqi.vo.PageImageVo;
import com.chuangqi.vo.WebPageVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.ResultCode;

import java.util.List;

/**
 * 页面图片控制类
 * @author jellyra
 */
@Slf4j
@RestController
@RequestMapping("image")
public class PageImageController extends BaseController{


    @Autowired
    private PageImageService     mService ;



    //图片列表视力
    @RequestMapping("list")
    public ModelAndView list() {
        modelAndView("image/list");
        return modelAndView ;
    }

    //创建新图片
    @RequestMapping("add")
    public void add(PageImageVo imageVo){
        //log.error("新页面数据，webPageVo={}",pageVo);
        Long newPage = mService.add(imageVo);
        sendOperationResult(newPage.intValue(), "新建图片");


    }
    // 新建图片视图
    @RequestMapping("addUI")
    public ModelAndView index(){
        modelAndView("image/addUI");
        return modelAndView;
    }

    // 图片数据
    @RequestMapping("data")
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
    @RequestMapping("update")
    public void  modify(PageImageVo imageVo){

        Long newPage = mService.updateByUqKey(imageVo);

        sendOperationResult(newPage.intValue(), "更新图片");
    }



}
