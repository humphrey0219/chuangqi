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
}
