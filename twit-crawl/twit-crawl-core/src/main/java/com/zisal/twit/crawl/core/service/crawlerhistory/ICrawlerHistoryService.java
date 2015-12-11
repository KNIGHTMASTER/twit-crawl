package com.zisal.twit.crawl.core.service.crawlerhistory;

import com.zisal.twit.crawl.core.service.IGenericService;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:13 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface ICrawlerHistoryService extends IGenericService {

    String findLatestHistory();
}
