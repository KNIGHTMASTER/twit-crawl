package com.zisal.twit.crawl.core.crawler.scheduler;

import com.zisal.twit.crawl.core.constant.ApplicationConstant.LogTag;
import com.zisal.twit.crawl.core.constant.ApplicationConstant.TwitterKey;
import com.zisal.twit.crawl.core.crawler.CrawlerComponent;
import com.zisal.twit.crawl.core.model.CrawlerHistory;
import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.crawlerhistory.ICrawlerHistoryService;
import com.zisal.twit.crawl.core.service.friend.IFriendshipService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import twitter4j.*;
import twitter4j.auth.AccessToken;


/**
 * Created on 11/19/15 5:39 PM.
 *
 * @author <a href="mailto:achmad.fauzi@sigma.co.id">Achmad Fauzi</a>
 */
@CrawlerComponent("jobRunner")
public class JobRunner implements IJobRunner {

    IFriendshipService friendshipService;
    ICrawlerHistoryService crawlerHistoryService;
    
    Logger logger = org.slf4j.LoggerFactory.getLogger(JobRunner.class);

    private String getCrawlerHistory(){
        String crawlerHistory = null;
        try{
            crawlerHistory = crawlerHistoryService.findLatestHistory();
        }catch (Exception e){
            e.printStackTrace();
        }
        return crawlerHistory;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info(LogTag.ZUNA_INFO, "start execute job");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        try {
            friendshipService = (IFriendshipService)jobDataMap.get("friendshipService");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            crawlerHistoryService = (ICrawlerHistoryService)jobDataMap.get("crawlerHistoryService");
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info(LogTag.ZUNA_INFO, "start twitter factory");
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(TwitterKey.CUSTOMER_KEY, TwitterKey.CUSTOMER_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(TwitterKey.TOKEN, TwitterKey.TOKEN_SECRET));
        long cursor = -1;
        PagableResponseList<User> pagableFollowings = null;
        String crawlerHistory = null;
        try{
            crawlerHistory = getCrawlerHistory();
            logger.info(LogTag.ZUNA_INFO, "get crawler history : "+crawlerHistory);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(crawlerHistory == null){
            do {
                try {
                    pagableFollowings = twitter.getFriendsList(twitter.getId(), cursor);
                    for (User user : pagableFollowings) {
                        logger.info(LogTag.ZUNA_INFO, "friend 1st level "+user.getName());
                        Friendship friendship = new Friendship();
                        friendship.setType(0);
                        friendship.setName(user.getName());
                        friendship.setImageUrl(user.getMiniProfileImageURL());
                        friendship.setCode(String.valueOf(user.getId()));
                        friendship.setLevel(1);
                        try{
                            friendshipService.save(friendship);
                        }catch (Exception e){
                            friendshipService.merge(friendship);
                        }
                        logger.info(LogTag.ZUNA_INFO, "friend " + user.getName() + "saved");
                    }
                } catch (Exception e) {
                    logger.error(LogTag.ZUNA_ERROR, "Error " + e.getLocalizedMessage());
                    break;
                }
            } while ((cursor = pagableFollowings.getNextCursor()) != 0);

            Friendship historyFriendship = friendshipService.getFirstFriendship();
            CrawlerHistory _1stLevelHistory = new CrawlerHistory();
            _1stLevelHistory.setCode(historyFriendship.getCode());
            crawlerHistoryService.save(_1stLevelHistory);
            logger.info(LogTag.ZUNA_INFO, "history 1st " + historyFriendship.getName() + " saved");
        }else{
            crawlerHistory = getCrawlerHistory();
            try {
                User user = twitter.showUser(Long.valueOf(crawlerHistory));
                logger.info(LogTag.ZUNA_INFO, "Restart scheduler from : "+user.getName());
                PagableResponseList<User> _2ndLevelPageableFollowings = twitter.getFriendsList(user.getId(), cursor);
                Friendship mainFriend = friendshipService.getFriendshipByCode(crawlerHistory);
                for(User _2ndLevelFriend : _2ndLevelPageableFollowings){
                    logger.info(LogTag.ZUNA_INFO, "friend 2nd level " + _2ndLevelFriend.getName());
                    Friendship friendship = new Friendship();
                    friendship.setType(0);
                    friendship.setName(_2ndLevelFriend.getName());
                    friendship.setImageUrl(_2ndLevelFriend.getMiniProfileImageURL());
                    friendship.setCode(String.valueOf(_2ndLevelFriend.getId()));
                    friendship.setLevel((mainFriend.getLevel() + 1));

                    mainFriend.getFriendships().add(friendship);
                    try{
                        friendshipService.save(mainFriend);
                    }catch (Exception e){
                        friendshipService.merge(mainFriend);
                    }
                    logger.info(LogTag.ZUNA_INFO, "Main Friend " + mainFriend.getName() + " saved");
                }
                Friendship nextFriendship = friendshipService.getNextFriendship(mainFriend);
                CrawlerHistory _2nsLevelHistory = new CrawlerHistory();
                _2nsLevelHistory.setCode(nextFriendship.getCode());
                crawlerHistoryService.save(_2nsLevelHistory);
                logger.info(LogTag.ZUNA_INFO, "history 2nd " + user.getName() + " saved");
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
    }
}
