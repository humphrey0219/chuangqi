package com.chuangqi.controller;

import com.chuangqi.bean.Paginer;
import com.chuangqi.service.OrgService;
import com.chuangqi.vo.OrgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 机构管理
 * @author jellyra
 */
@Slf4j
@RestController
public class OrgController extends BaseController{

    @Resource
    private OrgService mService ;


    @RequestMapping("org/listUI")
    public ModelAndView listUI() {
        return modelAndView("org/listUI");
    }


    @RequestMapping("org/addUI" )
    public  ModelAndView addUI(){
        return  modelAndView("org/addUI");


    }

    @RequestMapping("org/add")
    public void add(OrgVo v) {
        String action = "创建机构";
        try {
            Long result = mService.add(v) ;
            sendOperationResult(result.intValue(), action) ;
        }catch (Throwable e){
            log.error(action + " v={}, error", v, e);
            sendOperationResult(-1, action);
        }
    }

    @RequestMapping("org/del")
    public  void  del(OrgVo v) {
        String action = "删除机构";
        try {
            int  result = mService.del(v);
            sendOperationResult(result, action);
        }catch (Throwable e){
            log.error(action + " v={}, error", v, e);
            sendOperationResult(-1, action);
        }
    }

    @RequestMapping("org/data")
    public void  list(){
        String action = "读取机构列表";
        try {
            OrgVo v = new OrgVo();
            v.setWhereSql(getSearchRules());
            Paginer<OrgVo> paginer =getPaginer();
            paginer.setObj(v);
            paginer = mService.getPaginer(paginer);
            outPage(paginer);
        } catch (Throwable e){
            log.error(action + " error = {}", e);
        }
    }

}
