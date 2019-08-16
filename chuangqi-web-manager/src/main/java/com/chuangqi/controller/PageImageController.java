/**
 *
 */
package com.chuangqi.controller;

import com.alibaba.fastjson.JSONObject;
import com.chuangqi.bean.File;
import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.service.PageImageService;
import com.chuangqi.vo.PageImageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * 页面图片控制类
 * @author jellyra
 */
@Slf4j
@RestController

public class PageImageController extends BaseController{

    //页面图片目录
    @Value("${upload.file.image.web}")
    protected String  webImageDir;

    //预览图片临时目录
    @Value("${upload.file.image.web.temp}")

    protected String  webImageTempDir;

    @Value("${server.context-path}")
    private  String  contextPath ;

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
    private boolean delPic(String fileName){

        log.debug("删除图片； 文件名={}", fileName);
        boolean ok = true ;
        try{
            java.io.File des = new java.io.File(uploadFileBaseDir + webImageTempDir  + fileName);
            ok = des.delete();
        }catch (Throwable e){
            log.error("删除图片错误； error= {}", e);
            ok = false;
        }
        return ok ;
    }
    //删除图片记录
    @RequestMapping("image/del")
    public void del(PageImageVo v){
        boolean delPic = true;
        int ok = 2 ;
        try{
            //如果已创建数据
            if(v.getId() > 0){
               if(mService.del(v) == 0){
                   delPic = false;
                   ok = -1 ;
               }
            }
            //删除图片
            if(delPic){
                if(!delPic(v.getUrl())){
                    ok = -1;
                }

            }

            sendOperationResult(ok, "删除图片");

        }catch (Throwable e){
            log.error("删除图片数据错误 error: {}", e);
        }
    }

    //发布图片
    @RequestMapping("image/publish")
    public void publish(@RequestBody  List<PageImageVo> images ){
        try {
            for (int i = 0; i < images.size(); i++) {
                PageImageVo imageVo = images.get(i);

                String o = imageVo.getOldUrl();
                String n  = imageVo.getUrl() ;

                if(!o.equals(n)){
                    String webUrl = n.replace(resourceDomainUrl+webImageTempDir, resourceDomainUrl+webImageDir);
                    imageVo.setUrl(webUrl);
                }


                //更新图片

                if(imageVo.getId() > 0){



                    mService.updateByUqKey(imageVo);

                }else{
                    //新创建图片

                    mService.add(imageVo);

                }

                if(!o.equals(n)){

                    //log.info("修改 保存的url; url={}", webUrl);


                    moveFileToDes(n.substring(n.lastIndexOf("/")));

                    //原图片是否为共享
                    PageImageVo pageSharedImage = new PageImageVo();
                    pageSharedImage.setUrl(o);
                    //pageSharedImage.setWhereSql("url=\""+ o + "\"");
                    long count =mService.getCount(pageSharedImage);
                    log.info("图片引用数量={} url ={}",count);
                    if(count == 0){
                        log.info("图片不共享；删除 old={}, new={}", o, n);
                        String oldFileName = new StringBuilder()
                                .append(uploadFileBaseDir)
                                .append(webImageDir).append("/")
                                .append(o.substring(o.lastIndexOf("/"))).toString();
                        Path oldFile = Paths.get(oldFileName);
                        if(oldFile.toFile().exists()){

                            if(!oldFile.toFile().delete()){
                                log.error("删除旧图片失败； file name={}", oldFileName );
                            }
                        }
                    }
                }

            }
            log.error("图片数据,  jsonStr={}",  images);

            //删除临时目录中无用的图片

            sendOperationResult(9, "图片发布");

        }catch (Throwable e){
            log.error("图片发布异常,异常信息={}",  e);
            sendOperationResult(-1, "图片发布");
        }finally {
            delTempImage();
        }

    }

    private void  moveFileToDes(String fileName){
        //java.io.File f = new java.io.File("des");
        log.info("Move file to des; file name={}", fileName);
        Path source = null ;
        Path des  = null ;
        try{
            source= Paths.get(uploadFileBaseDir + webImageTempDir + "/" + fileName);
           des = Paths.get(uploadFileBaseDir + webImageDir + "/" + fileName);
            java.io.File copyFile = des.toFile() ;
           if(!copyFile.exists()){
               if(!copyFile.mkdirs()){
                   log.error("创建文件失败 file={}", copyFile);
               }
           }
            Files.move(source, des, StandardCopyOption.REPLACE_EXISTING);
        }catch (Throwable e){
            log.error("Move图片失败; source ={}, des={}, error={}", source, des, e);
        }

    }

    private void delTempImage(){
        Path tempPath = Paths.get(uploadFileBaseDir + webImageTempDir);

        try {
            java.io.File[] files =tempPath.toFile().listFiles();
            for(int i = 0 ; i < files.length ; i++) {
                java.io.File f = files[i] ;
                if(!f.delete()){
                    log.error("删除文件失败 file={}",f);

                }
            }

        }catch (Throwable e){
            log.error("删除临时文件失败 error={}", e);
        }
    }



    // 保存上传图片
    @RequestMapping("image/save")
    public void saveImage(File file){

        MultipartFile f = file.getFile();

        String uuidName = UUID.randomUUID().toString() + f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));



        log.error("保存图片 file {} name {}, size {} , uuidName {} ",f, f.getOriginalFilename(), f.getSize(), uuidName);
        //查看存储目录是否存在
        java.io.File baseDir = new java.io.File(uploadFileBaseDir + webImageTempDir); //  + f.getOriginalFilename());
        if(!baseDir.exists()) {
           if(!baseDir.mkdirs()) {
               sendOperationResult(-1, "上传图片");
               return ;
           }
        }

        baseDir = new java.io.File(baseDir.getAbsolutePath() + "/" +  uuidName);



        log.info("图片地址 {}", baseDir.getAbsolutePath());

        try {
            f.transferTo(baseDir);
            sendOperationResult(1, resourceDomainUrl  + webImageTempDir + "/" + uuidName);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("保存出错 {}", e);
            sendOperationResult(-1, "保存图片");
        }
    }
    @RequestMapping("image/data")
    public void listData(){
        try {
            PageImageVo v = new PageImageVo();
            v.setWhereSql(getSearchRules());
            Paginer<PageImageVo> painter = getPaginer();
            painter.setObj(v);
            painter = mService.getPaginer(painter);
            outPage(painter);
        }catch (Throwable e){
            log.error("读取页面图片列表错误 error = {}", e);
        }

    }

    // 新建,更新页面图片视图
    @RequestMapping("image/addUI/{pageNum}")
    public ModelAndView index(@PathVariable String pageNum){
        PageImageVo pageImageVo = new PageImageVo() ;

        try{
                pageImageVo.setPageNum(pageNum);

                List images = mService.getList(pageImageVo);

                String imgStr = JSONObject.toJSONString(images);

            modelAndView("image/addUI");
            return modelAndView.addObject("images", imgStr);

        }catch (Throwable e){
            log.error("读取图片数据异常： pageImageVo = {} error = {}", pageImageVo, e);
            return modelAndView("error");
        }


    }

  // 页面图片，一个页面的所有图片数据
    @RequestMapping("image/data/{pageNum}")
    public void allPage(@PathVariable String pageNum){
        PageImageVo pageImageVo = new PageImageVo() ;
        try{

            pageImageVo.setPageNum(pageNum);

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


    }




}
