package com.zisal.twit.crawl.core;

import com.zisal.twit.crawl.core.model.CrawlerHistory;
import com.zisal.twit.crawl.core.service.crawlerhistory.ICrawlerHistoryService;
import com.zisal.twit.crawl.core.service.friend.IFriendshipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Ladies Man on 11/21/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = "classpath:/META-INF/spring/ApplicationModule.xml"
)
public class TestDeepCrawler {

    @InjectMocks
    @Autowired
    private IFriendshipService iFriendshipService;

    @InjectMocks
    @Autowired
    private ICrawlerHistoryService crawlerHistoryService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSetup(){
        CrawlerHistory crawlerHistory = new CrawlerHistory();
        crawlerHistory.setCode("1");
        crawlerHistoryService.save(crawlerHistory);
    }
}
