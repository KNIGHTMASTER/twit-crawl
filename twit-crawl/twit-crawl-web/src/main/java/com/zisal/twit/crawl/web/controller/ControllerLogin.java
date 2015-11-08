package com.zisal.twit.crawl.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ladies Man on 11/8/2015.
 */
@Controller
public class ControllerLogin {

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
