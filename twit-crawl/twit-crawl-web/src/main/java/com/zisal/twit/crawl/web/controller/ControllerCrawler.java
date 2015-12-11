package com.zisal.twit.crawl.web.controller;

import com.zisal.twit.crawl.core.crawler.scheduler.IScheduledCrawler;
import com.zisal.twit.crawl.web.handler.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fauzi on 11/2/15.
 */
@Controller
@RequestMapping(value = "/crawler")
public class ControllerCrawler {

    @Autowired
    @Qualifier(value = "scheduledCrawler")
    IScheduledCrawler iScheduledCrawler;

    @RequestMapping(value = "/scheduler")
    @Layout(value = "index")
    public ModelAndView getFollowerList(){
       iScheduledCrawler.executeScheduler();
        ModelAndView modelAndView = new ModelAndView("crawler_scheduler", "", "");
        return modelAndView;
    }

}
