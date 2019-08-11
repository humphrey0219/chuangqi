package com.chuangqi.controller;


import com.chuangqi.bean.GridData;
import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.controller.BaseController;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.vo.SysAccountVo;
import com.chuangqi.vo.WebPageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
        return  modelAndView("account/addUi");

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
        SysAccountVo v = new SysAccountVo();

        try{
            v.setWhereSql(getSearchRules());
            v.setOrderBySql(" order by id desc ");
            Paginer<SysAccountVo> paginer =getPaginer();
            paginer.setObj(v);
            paginer = sysAccountService.getPaginer(paginer);
            outPage(paginer);
        }catch (Throwable e){
                e.printStackTrace();
                log.error("读取用户数据错误 error = {}", e);

        }



    }

    //创建新账户
    @RequestMapping("account/add")
    public void  add(SysAccountVo accountVo) {
    	try {
    		SysAccountVo accountVoQy=new SysAccountVo();
    		accountVoQy.setUserName(accountVo.getUserName());
    		long rt=sysAccountService.getCount(accountVoQy);
    		if(rt>=1){
    			 ResultCode resultCode = ResultCode.newSuccess();
    			 resultCode.setFail("该用户名已存在");
    			 resWriteObjectJson(resultCode);
    			 return ;
    		}
    		 Long id = sysAccountService.add(accountVo);

    	       sendOperationResult(id.intValue(), "创建账户");
		} catch (Throwable e) {
			log.error("创建账号异常", e);
		}
       

    }
    @RequestMapping("account/updateUI")
    public ModelAndView updatUI(){
        modelAndView("account/updateUI");
        return  modelAndView;
    }

    // 删除账户
    @RequestMapping("account/del")
    public void  del(Long id) {
        SysAccountVo vo = new SysAccountVo();

        vo.setId(id);

        log.info("账号 id : {}", vo);
        int resultId  = sysAccountService.del(vo);
        log.info("删除用户账号 id: {}", resultId);

        sendOperationResult(resultId, "删除账号成功");

    }

    // 更新账户
    @RequestMapping("account/update")
    public void  updateByKey(SysAccountVo vo) {
        try{
            Long resultId = sysAccountService.updateByUqKey(vo);
            log.info("更改账号信息 结果为 {}", resultId);
            sendOperationResult(resultId.intValue(), "更新账号");
        }catch (Throwable e){
            e.printStackTrace();
            sendOperationResult(-1, "更新账号");
        }


    }






}
