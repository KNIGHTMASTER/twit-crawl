package com.zisal.twit.crawl.core.dao.crawlerhistory;


import com.zisal.twit.crawl.core.dao.GenericDAOJPAImpl;
import com.zisal.twit.crawl.core.model.CrawlerHistory;
import com.zisal.twit.crawl.core.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Repository("crawlerHistoryDAO")
public class CrawlerHistoryDAOImpl extends GenericDAOJPAImpl implements ICrawlerHistoryDAO {

    @Override
    public String findLatestHistory() {
        String result = null;
        try{
            Query query = entityManager.createQuery("select max(a.id) from crawler_history a", Integer.class);
            Integer historyId = (Integer) query.getSingleResult();
            query = entityManager.createQuery("select a.code from crawler_history a where a.id = :historyId");
            query.setParameter("historyId", historyId);
            result = (String) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
