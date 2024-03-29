package com.chuangqi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class IndexController extends BaseController {

    //主页视图
    @RequestMapping("index")
    public ModelAndView index() {
        return  modelAndView("home/indexUI");

    }
}
