package com.chuangqi.controller;

import com.chuangqi.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页控制器
 * @author jellyra
 */
@Slf4j
@Controller

public class HomeController extends BaseController {


    //主页视图
    @RequestMapping("web/index")
    public ModelAndView index() {
        return  modelAndView("home/indexUI");

    }

    @RequestMapping("web/about")
    public ModelAndView about(){
        return modelAndView("aboutUI");
    }

    @RequestMapping("web/test")
    public ModelAndView smiletest(){
        String nav = "blue";
        modelAndView("test/indexUI");
        modelAndView.addObject("nav", nav);

        return modelAndView ;
    }


}
