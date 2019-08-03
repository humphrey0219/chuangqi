/**
 *
 */
package com.chuangqi.controller;

import com.chuangqi.bean.GridData;
import com.chuangqi.service.WebPageService;
import com.chuangqi.vo.WebPageVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.constant.Constant;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.vo.SysAccountVo;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 页面管理控制类
 * @author jellyra
 */
@Slf4j
@RestController
public class WebPageController extends BaseController{


    @Autowired
    private WebPageService     webPageService ;



    //页面列表页面
    @RequestMapping("webpage/list")
    public ModelAndView list() {
        modelAndView("webpage/list");
        return modelAndView ;
    }

    //创建新页面
    @RequestMapping("page/create")
    public void create(WebPageVo pageVo){
        log.error("新页面数据，webPageVo={}",pageVo);
          Long newPage = webPageService.add(pageVo);
          ResultCode resultCode = ResultCode.newSuccess();

        //若创建成功后，新页面的ID作为 Msg 返回客户，在前端通过 result code 判定操作是否成功
          if(newPage == null ) {

              resultCode.setFail("新页面创建失败，请检查页面是否设置正确！");
          }else {

              resultCode.setMsg(newPage.toString());
          }

          resWriteObjectJson(resultCode);



    }

    @RequestMapping("webpage/add")
    public ModelAndView index(){
        modelAndView("webpage/add");
        return modelAndView;
    }


    @RequestMapping("webpage/data")
    public void allPage(){
       List pages = webPageService.getList(new WebPageVo());
        GridData data = new GridData(pages, pages.size());
       if(pages != null ){
           resWriteObjectJson(data);
       }else{
           ResultCode resultCode =ResultCode.newSuccess();
           resultCode.setFail("数据不可用");
           resWriteObjectJson(resultCode);
       }

    }
    // 修改页面
    @RequestMapping("page/update")
    public void  modify(WebPageVo pageVo){

        Long newPage = webPageService.updateByUqKey(pageVo);

        sendOperationResult(newPage.intValue(), "更新页面");
    }



}
