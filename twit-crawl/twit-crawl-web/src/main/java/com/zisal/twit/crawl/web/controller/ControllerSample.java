package com.zisal.twit.crawl.web.controller;

import com.zisal.twit.crawl.web.handler.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ladies Man on 11/7/2015.
 */
@Controller
public class ControllerSample {

    @RequestMapping(value = "/sample1")
    @Layout(value = "index")
    public String samplePage(){
        return "sample";
    }

    @RequestMapping(value = "/sample2")
    @Layout(value = "index")
    public String samplePage2(){
        return "Sample2";
    }
}
