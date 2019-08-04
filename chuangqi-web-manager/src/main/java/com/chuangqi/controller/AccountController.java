package com.chuangqi.controller;


import com.chuangqi.bean.GridData;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.controller.BaseController;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.vo.SysAccountVo;
import com.chuangqi.vo.WebPageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 账号管理控制类
 * @author jellyra
 */
@Slf4j
@RestController
public class AccountController extends BaseController {


    @Autowired
    private SysAccountService sysAccountService;


    //创建账户视图
    @RequestMapping("account/addUi")
    public ModelAndView addUi() {
        modelAndView("account/addUi");
        return modelAndView ;
    }

    //列表视图
    @RequestMapping("account/list")
    public ModelAndView list() {
        modelAndView("account/list");
        return modelAndView ;
    }

    //所有用户数据
    @RequestMapping("account/data")
    public void allPage(){
        List pages = sysAccountService.getList(new SysAccountVo());
        GridData data = new GridData(pages, pages.size());
        if(pages != null ){
            resWriteObjectJson(data);
        }else{
            ResultCode resultCode =ResultCode.newSuccess();
            resultCode.setFail("数据不可用");
            resWriteObjectJson(resultCode);
        }

    }

    //创建新账户
    @RequestMapping("account/add")
    public void  add(SysAccountVo accountVo) {

        Long id = sysAccountService.add(accountVo);

        sendOperationResult(id.intValue(), "创建账户");

    }

    // 删除账户
    @RequestMapping("account/del")
    public void  del(Long id) {
        SysAccountVo vo = new SysAccountVo();
        vo.setId(id);

        log.info("账号 id : {}", vo);
        int resultId  = sysAccountService.del(vo);
        log.info("删除用户账号 id: {}", resultId);

        sendOperationResult(resultId, "删除账号");

    }

    // 更新账户
    @RequestMapping("account/update")
    public void  updateByKey(SysAccountVo vo) {

       Long resultId = sysAccountService.updateByUqKey(vo);
       log.info("更改账号信息 结果为 {}", resultId);
        sendOperationResult(resultId.intValue(), "更新账号");
    }






}
