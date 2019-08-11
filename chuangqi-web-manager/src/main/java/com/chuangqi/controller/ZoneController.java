package com.chuangqi.controller;

import com.chuangqi.bean.Paginer;
import com.chuangqi.service.ZoneService;
import com.chuangqi.vo.ZoneVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 区域地址管理
 * @author jellyra
 */
@Slf4j
@RestController
public class ZoneController extends BaseController{

    @Resource
    private ZoneService mService ;

    @RequestMapping("zone/listUI")
    public ModelAndView listUI() {
        return modelAndView("zone/listUI");
    }


    @RequestMapping("zone/addUI")
    public  ModelAndView addView(){
        return modelAndView("zone/add");
    }

    @RequestMapping("zone/add")
    public void add(ZoneVo v) {
        String action = "创建地址";
        try {
            Long result = mService.add(v) ;
            sendOperationResult(result.intValue(), action) ;
        }catch (Throwable e){
            log.error(action + " v={}, error", v, e);
            sendOperationResult(-1, action);
        }
    }

    @RequestMapping("zone/del/{id}")
    public  void  del(@PathVariable Long id) {
        String action = "删除地址";
        ZoneVo v = new ZoneVo();
        v.setId(id);
        try {
            int  result = mService.del(v);
            sendOperationResult(result, action);
        }catch (Throwable e){
            log.error(action + " v={}, error", v, e);
            sendOperationResult(-1, action);
        }
    }

    @RequestMapping("zone/update")
    public void update(ZoneVo v) {
        String action = "创建地址";
        try {
            Long result = mService.updateByUqKey(v) ;
            sendOperationResult(result.intValue(), action) ;
        }catch (Throwable e){
            log.error(action + " v={}, error", v, e);
            sendOperationResult(-1, action);
        }
    }

    @RequestMapping("zone/data")
    public void  list(){
        String action = "读取区域地址";
        try {
            ZoneVo v = new ZoneVo();
            v.setWhereSql(getSearchRules());
            Paginer<ZoneVo> paginer =getPaginer();
            paginer.setObj(v);
            paginer = mService.getPaginer(paginer);
            outPage(paginer);
        } catch (Throwable e){
            log.error(action + " error = {}", e);
        }
    }

}
