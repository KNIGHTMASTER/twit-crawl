package com.zisal.twit.crawl.core.crawler;

import com.zisal.twit.crawl.core.constant.ApplicationConstant;
import com.zisal.twit.crawl.core.dto.DTOFriendship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fauzi on 11/2/15.
 */
@CrawlerComponent(value = "crawler")
public class CrawlerImpl implements ICrawler {

    Logger logger = LoggerFactory.getLogger(CrawlerImpl.class);

    protected  Twitter twitter = new TwitterFactory().getInstance();

    public Twitter getTwitter() {
        return twitter;
    }

    public CrawlerImpl(){
        twitter.setOAuthConsumer(ApplicationConstant.Twitter.CUSTOMER_KEY, ApplicationConstant.Twitter.CUSTOMER_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(ApplicationConstant.Twitter.TOKEN, ApplicationConstant.Twitter.TOKEN_SECRET));
    }

    @Override
    public List<DTOFriendship> getFollowers(String screenName) {
        List<DTOFriendship> result = new ArrayList<>();
        DTOFriendship DTOFriendship;
        long cursor = -1;
        PagableResponseList<User> pagableFollowers = null;
        do {
            try {
                pagableFollowers = twitter.getFollowersList(twitter.getId(), cursor);
                for (User user : pagableFollowers) {
                    System.out.println(user.getName());
                    System.out.println("Followers "+user.getName());
                    DTOFriendship = new DTOFriendship();
                    DTOFriendship.setId(user.getId());
                    DTOFriendship.setName(user.getName());
                    DTOFriendship.setImageUrl(user.getMiniProfileImageURL());
                    result.add(DTOFriendship);
                }
            } catch (TwitterException e) {
                logger.error(ApplicationConstant.LOG.ZUNA_ERROR, e.getLocalizedMessage());
            }
        } while ((cursor = pagableFollowers.getNextCursor()) != 0);
        return result;
    }

    @Override
    public List<DTOFriendship> getFriends(String screenName) {
        List<DTOFriendship> result = new ArrayList<>();
        long cursor = -1;
        PagableResponseList<User> pagableFollowings = null;
        do {
            try {
                pagableFollowings = twitter.getFriendsList(twitter.getId(), cursor);
                for (User user : pagableFollowings) {
                    DTOFriendship dtoFriendship = new DTOFriendship();
                    dtoFriendship.setId(user.getId());
                    dtoFriendship.setName(user.getName());
                    dtoFriendship.setImageUrl(user.getMiniProfileImageURL());
                    result.add(dtoFriendship);
                }
            } catch (TwitterException e) {
                logger.error(ApplicationConstant.LOG.ZUNA_ERROR, e.getLocalizedMessage());
            }

        } while ((cursor = pagableFollowings != null ? pagableFollowings.getNextCursor() : 0) != 0);
        return result;
    }
}
