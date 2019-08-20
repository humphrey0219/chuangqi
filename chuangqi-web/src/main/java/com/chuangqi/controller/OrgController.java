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


}
