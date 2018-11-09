package com.mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wy on 2018/11/5.
 */
@Controller
public class IndexController {

    @RequestMapping("toMainPage")
    public String toMainPage(){
        return "main";
    }

    @RequestMapping("toRegPage")
    public String toRegPage(){
        return "reg";
    }





}


