package com.zisal.twit.crawl.core.crawler.scheduler;

import com.zisal.twit.crawl.core.constant.ApplicationConstant;
import com.zisal.twit.crawl.core.crawler.CrawlerComponent;
import com.zisal.twit.crawl.core.service.crawlerhistory.ICrawlerHistoryService;
import com.zisal.twit.crawl.core.service.friend.IFriendshipService;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Date;

/**
 * Created on 11/19/15 5:36 PM.
 *
 * @author <a href="mailto:achmad.fauzi@sigma.co.id">Achmad Fauzi</a>
 */
@CrawlerComponent(value = "scheduledCrawler")
public class ScheduledCrawlerImpl implements IScheduledCrawler {

    @Autowired
    IFriendshipService friendshipService;

    @Autowired
    ICrawlerHistoryService crawlerHistoryService;

    @Autowired
    ApplicationContext applicationContext;

    Logger logger = LoggerFactory.getLogger(ScheduledCrawlerImpl.class);

    @Override
    public void executeScheduler() {
        JobDetailImpl jobDetail  = new JobDetailImpl();
        jobDetail.setName("Crawler Job");
        jobDetail.setJobClass(JobRunner.class);

        //configure the scheduler time
        SimpleTriggerImpl trigger = new SimpleTriggerImpl();
        trigger.setName("Trigger Crawler");
        trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setRepeatInterval(960000);// per 16 minutes

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("friendshipService", friendshipService);
        jobDataMap.put("crawlerHistoryService", crawlerHistoryService);
        jobDetail.setJobDataMap(jobDataMap);

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        Scheduler scheduler;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.setJobFactory(jobFactory);
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info(ApplicationConstant.LogTag.ZUNA_INFO, "run the scheduler");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}