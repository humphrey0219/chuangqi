package com.chuangqi.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController

public class SmileTestController  extends BaseController {
    @RequestMapping("smile")
    public ModelAndView smiletest(){
        String nav = "blue";
        modelAndView("smile/indexUI");
        modelAndView.addObject("nav", nav);

        return modelAndView ;
    }

    @RequestMapping("pic")
    public ModelAndView uploadPic(){
        return modelAndView("smile/upfileUI");
    }

    @RequestMapping("contact")
    public ModelAndView contact(){
        return modelAndView("smile/contactUI");
    }

    @RequestMapping("result")
    public ModelAndView result(){
        return modelAndView("smile/resultUI");
    }

    @RequestMapping("think")
    public ModelAndView thick(){
         modelAndView("smile/thinkUI");
        modelAndView.addObject("nav", "nav");
        return modelAndView ;
    }
}
