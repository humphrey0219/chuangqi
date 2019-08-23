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
@RequestMapping("/home")
public class HomeController extends BaseController {


    //主页视图
    @RequestMapping("index")
    public ModelAndView index() {
        return  modelAndView("home/indexUI");

    }
}
