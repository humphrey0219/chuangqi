/**
 *
 */
package com.chuangqi.controller;

import com.alibaba.fastjson.JSONObject;
import com.chuangqi.bean.File;
import com.chuangqi.service.PageImageService;
import com.chuangqi.vo.PageImageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



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
        return modelAndView("image/list");
    }

    //创建新图片
    @RequestMapping("image/add")
    public void add(PageImageVo imageVo){
        //log.error("新页面数据，webPageVo={}",pageVo);
        Long newPage = mService.add(imageVo);
        sendOperationResult(newPage.intValue(), "新建图片");


    }

    //发布图片
    @RequestMapping("image/publish")
    public void publish(@RequestBody  List<PageImageVo> images ){
        try {
            for (int i = 0; i < images.size(); i++) {
                PageImageVo imageVo = images.get(i);

                //更新图片
                if(imageVo.getId() > 0){
                    mService.updateByUqKey(imageVo);
                }else{
                    //新创建图片
                    mService.add(imageVo);
                }

            }
            log.error("图片数据,  jsonStr={}",  images);

            sendOperationResult(9, "图片发布");

        }catch (Throwable e){
            log.error("图片发布异常,异常信息={}",  e);
            sendOperationResult(-1, "图片发布");
        }

    }

    // 保存上传图片
    @RequestMapping("image/save")
    public void saveImage(File file){

        MultipartFile f = file.getFile();


        log.error("保存图片 file {} name {}, size {} ",f, f.getOriginalFilename(), f.getSize());


        java.io.File des = new java.io.File(IMAGE_PAGE  + f.getOriginalFilename());

        log.error("图片地址 {}", des.getAbsolutePath());

        try {
            f.transferTo(des);
            sendOperationResult(1, "image/"+ f.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("保存出错 {}", e);
            sendOperationResult(-1, "保存图片");
        }
    }

    // 新建,更新页面图片视图
    @RequestMapping("image/addUI/{page}")
    public ModelAndView index(@PathVariable Long page){
        PageImageVo pageImageVo = new PageImageVo() ;

        try{
                pageImageVo.setPage(page);

                List images = mService.getList(pageImageVo);

                String imgStr = JSONObject.toJSONString(images);

            modelAndView("image/addUI");
            return modelAndView.addObject("images", imgStr);

        }catch (Throwable e){
            log.error("读取图片数据异常： pageImageVo = {} error = {}", pageImageVo, e);
            return modelAndView("error");
        }


    }

/*    // 页面图片，一个页面的所有图片数据
    @RequestMapping("image/data/{page}")
    public void allPage(@PathVariable Long page){
        PageImageVo pageImageVo = new PageImageVo() ;
        try{

            pageImageVo.setPage(page);

            List pages = mService.getList(pageImageVo);
            //GridData data = new GridData(pages, pages.size());
            if(pages != null ){
                resWriteObjectJson(pages);
            }else{
                ResultCode resultCode =ResultCode.newSuccess();
                resultCode.setFail("数据不可用");
                resWriteObjectJson(resultCode);
            }
        }catch (Throwable e){
            sendOperationResult(-1, "读取图片数据");
            log.error("读取图片数据异常： imagevo = {} error = {}", pageImageVo, e);
        }


    }*/




}
