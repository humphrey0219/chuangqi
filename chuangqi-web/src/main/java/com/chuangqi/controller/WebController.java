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

public class WebController extends BaseController {


    //主页视图
    @RequestMapping("home")
    public ModelAndView index() {
        return  modelAndView("home/indexUI");

    }

    @RequestMapping("about")
    public ModelAndView about(){
        return modelAndView("aboutUI");
    }

    @RequestMapping("smile")
    public ModelAndView smiletest(){
        String nav = "blue";
        modelAndView("smile/indexUI");
        modelAndView.addObject("nav", nav);

        return modelAndView ;
    }


}
