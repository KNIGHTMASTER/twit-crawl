package com.zisal.twit.crawl.web.controller;

import com.zisal.twit.crawl.core.model.AppConfiguration;
import com.zisal.twit.crawl.core.service.appconfiguration.IAppConfigurationService;
import com.zisal.twit.crawl.web.handler.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Ladies Man on 12/11/2015.
 */
@Controller
public class ControllerAppConfiguration {

    @Autowired
    @Qualifier("appConfigurationService")
    IAppConfigurationService appConfigurationService;

    @RequestMapping(value = "/appConfiguration")
    @Layout(value = "index")
    public ModelAndView appConfiguration(){
        return new ModelAndView(
                "app_configuration",
                "appConfigurationList",
                appConfigurationService.loadAllEntity(AppConfiguration.class)
        );
    }

}
