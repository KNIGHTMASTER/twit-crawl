package com.zisal.twit.crawl.core.service.crawlerhistory;

import com.zisal.twit.crawl.core.dao.IGenericDAO;
import com.zisal.twit.crawl.core.dao.crawlerhistory.ICrawlerHistoryDAO;
import com.zisal.twit.crawl.core.model.CrawlerHistory;
import com.zisal.twit.crawl.core.service.GenericServiceJPAImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:14 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Service("iCrawlerHistoryService")
public class CrawlerHistoryServiceImpl extends GenericServiceJPAImpl implements ICrawlerHistoryService {

    private ICrawlerHistoryDAO crawlerHistoryDAO;

    @Autowired
    public CrawlerHistoryServiceImpl(@Qualifier("crawlerHistoryDAO") IGenericDAO genericDAO) {
        super(genericDAO);
        this.crawlerHistoryDAO = (ICrawlerHistoryDAO) genericDAO;
    }

    @Override
    public String findLatestHistory() {
        return crawlerHistoryDAO.findLatestHistory();
    }
}
