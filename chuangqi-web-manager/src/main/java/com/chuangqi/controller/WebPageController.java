/**
 *
 */
package com.chuangqi.controller;

import com.chuangqi.bean.GridData;
import com.chuangqi.bean.Paginer;
import com.chuangqi.service.WebPageService;
import com.chuangqi.vo.PageImageVo;
import com.chuangqi.vo.WebPageVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.ResultCode;

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

    //页面列表数据
    @RequestMapping("webpage/data")
    public void allPage(){
        try {

            WebPageVo v = new WebPageVo();
            v.setWhereSql(getSearchRules());
            Paginer<WebPageVo> paginer = getPaginer();
            paginer.setObj(v);
            paginer = webPageService.getPaginer(paginer);
            outPage(paginer);

        }catch (Throwable e){
            log.error("读取用户数据错误 error={}", e);
        }


    }
    //删除页面
    @RequestMapping("webpage/del")
    public void  del( Long id){
        try {
            WebPageVo v = new WebPageVo();
            v.setId(id);
            webPageService.del(v);
            sendOperationResult(1, "已删除页面");
        }catch (Throwable e){
            log.error("删除页面失败 pageID = {}  error = {}", id, e);
            sendOperationResult(-1, "删除页面");
        }
    }

    //所有页面数据 comboBox
    @RequestMapping("webpage/pages")
    public void pages(){
        try {
            List pages = webPageService.getList(new WebPageVo());
            resWriteObjectJson(pages);
        } catch (Throwable e){
            sendOperationResult(-1, "页面数据");
        }

    }

    // 修改页面
    @RequestMapping("page/update")
    public void  modify(WebPageVo pageVo){
        try{
            Long newPage = webPageService.updateByUqKey(pageVo);

            sendOperationResult(newPage.intValue(), "更新页面");
        }catch (Throwable e){
            log.error("修改页面错误；更新的页面数据={} error={}", pageVo,  e);
            sendOperationResult(0, "更新页面");
        }

    }



}
