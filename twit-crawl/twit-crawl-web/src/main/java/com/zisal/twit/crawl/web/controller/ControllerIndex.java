package com.zisal.twit.crawl.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Ladies Man on 11/1/2015.
 */
@Controller
@RequestMapping(value = "/")
public class ControllerIndex {

    @RequestMapping(method = RequestMethod.GET)
    public String helloIndex(Principal principal){
        return principal != null ? "homeSignedIn" : "homeNotSignedIn";
    }

    @RequestMapping(value = "hello")
    public String helloWorld(ModelMap model){
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @RequestMapping(value = "testForm")
    public String testForm(){
        return "test_form";
    }

    @RequestMapping(value = "actionForm")
    public String actionForm(ModelMap model){
        model.addAttribute("message", "action form");
        return "test_form";
    }
}
