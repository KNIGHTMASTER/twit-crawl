/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zisal.twit.crawl.core;

import com.zisal.twit.crawl.core.constant.ApplicationConstant;
import org.slf4j.*;
import org.slf4j.Logger;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class Example {

    static Logger logger = org.slf4j.LoggerFactory.getLogger(Example.class);
    public static void main(String [] args){
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(ApplicationConstant.TwitterKey.CUSTOMER_KEY, ApplicationConstant.TwitterKey.CUSTOMER_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(ApplicationConstant.TwitterKey.TOKEN, ApplicationConstant.TwitterKey.TOKEN_SECRET));
        /*try{
            ResponseList<Status> responseList = twitter.getUserTimeline(new Paging(1, 5));
            for(Status s: responseList){
                System.out.println("Response List ".concat(s.getText()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        long cursor = -1;
        IDs ids = null;
        System.out.println("Listing followers's ids.");
        do {
            try {
                ids = twitter.getFollowersIDs(ApplicationConstant.Twitter.SCREEN_NAME, cursor);
                for (long id : ids.getIDs()) {
                    System.out.println(id);
                    User user = twitter.showUser(id);
                    System.out.println(user.getName());
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        } while ((cursor = ids != null ? ids.getNextCursor() : 0) != 0);*/

        long cursor = -1;
        PagableResponseList<User> pagableFollowings = null;
        List<User> listFriends = new ArrayList<>();
        do {
            try {
                pagableFollowings = twitter.getFriendsList(twitter.getId(), cursor);
                for (User user : pagableFollowings) {
                    listFriends.add(user);
                    logger.info(ApplicationConstant.LogTag.ZUNA_INFO, "friend #1st level "+user.getName());
                    PagableResponseList<User> _2ndLevelPageableFollowings = twitter.getFriendsList(user.getId(), cursor);
                    for(User _2ndLevelFriend : _2ndLevelPageableFollowings){
                        logger.info(ApplicationConstant.LogTag.ZUNA_INFO, "added friend #2nd level "+ _2ndLevelFriend.getName());
                        listFriends.add(_2ndLevelFriend);
                    }
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }

        } while ((cursor = pagableFollowings.getNextCursor()) != 0);

        logger.info(ApplicationConstant.LogTag.ZUNA_INFO, "friend total : " + listFriends.size());
        for(User user:listFriends){
            logger.info(ApplicationConstant.LogTag.ZUNA_INFO, "friend : "+user.getName());
        }

/*
        cursor = -1;
        PagableResponseList<User> pagableFollowers = null;
        List<User> listFollowers = new ArrayList<>();
        do {
            try {
                pagableFollowers = twitter.getFollowersList(twitter.getId(), cursor);
                for (User user : pagableFollowers) {
                    listFollowers.add(user); // ArrayList<User>
                    System.out.println(user.getName());
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }

        } while ((cursor = pagableFollowers.getNextCursor()) != 0);
*/

    }


}
