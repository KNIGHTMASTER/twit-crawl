package com.zisal.twit.crawl.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ladies Man on 11/1/2015.
 */
@Controller
public class ControllerIndex {

    @RequestMapping(value = "/")
    public String helloWorld(ModelMap model){
        model.addAttribute("message", "Hello world!");
        return "index";
    }
}
