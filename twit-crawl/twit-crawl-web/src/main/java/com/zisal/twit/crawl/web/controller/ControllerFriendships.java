package com.zisal.twit.crawl.web.controller;

import com.zisal.twit.crawl.core.constant.ApplicationConstant;
import com.zisal.twit.crawl.core.crawler.ICrawler;
import com.zisal.twit.crawl.core.dto.DTOFriendship;
import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.friend.IFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fauzi on 11/2/15.
 */
@Controller
@RequestMapping(value = "/friendship")
public class ControllerFriendships {

    @Autowired
    @Qualifier(value = "crawler")
    ICrawler crawler;

    @Autowired
    @Qualifier(value = "iFriendshipService")
    IFriendshipService friendshipService;

    @RequestMapping(value = "/follower/list")
    public ModelAndView getFollowerList(){
        List<Friendship> localFollowers;
        List<DTOFriendship> followers = new ArrayList<DTOFriendship>();
        try{
            localFollowers = friendshipService.getAllFollowers();
            if(localFollowers.size() > 0){
                for(Friendship friendship: localFollowers){
                    DTOFriendship dtoFriendship = new DTOFriendship();
                    dtoFriendship.setId(Long.parseLong(friendship.getCode()));
                    dtoFriendship.setName(friendship.getName());
                    dtoFriendship.setImageUrl(friendship.getImageUrl());
                    followers.add(dtoFriendship);
                }
            }else{
                try{
                    followers = crawler.getFollowers(ApplicationConstant.Twitter.SCREEN_NAME);
                    for(DTOFriendship friendship: followers){
                        System.out.println("Friendship "+friendship.toString());
                        Friendship friendshipModel = new Friendship();
                        friendshipModel.setCode(String.valueOf(friendship.getId()));
                        friendshipModel.setName(friendship.getName());
                        friendshipModel.setImageUrl(friendship.getImageUrl());
                        friendshipModel.setType(1);
                        friendshipService.save(friendshipModel);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ModelAndView("follower_list", "followers", followers);
    }

    @RequestMapping(value = "/friend/list")
    public ModelAndView getFriendList(){
        List<Friendship> localFriends;
        List<DTOFriendship> friendships = new ArrayList<DTOFriendship>();
        try{
            localFriends = friendshipService.getAllFriendship();
            if(localFriends.size() > 0){
                for(Friendship friendship: localFriends){
                    DTOFriendship dtoFriendship = new DTOFriendship();
                    dtoFriendship.setId(Long.parseLong(friendship.getCode()));
                    dtoFriendship.setName(friendship.getName());
                    dtoFriendship.setImageUrl(friendship.getImageUrl());
                    friendships.add(dtoFriendship);
                }
            }else{
                try{
                    friendships = crawler.getFriends(ApplicationConstant.Twitter.SCREEN_NAME);
                    for(DTOFriendship friendship: friendships){
                        Friendship friendshipModel = new Friendship();
                        friendshipModel.setCode(String.valueOf(friendship.getId()));
                        friendshipModel.setName(friendship.getName());
                        friendshipModel.setImageUrl(friendship.getImageUrl());
                        friendshipModel.setType(0);
                        friendshipService.save(friendshipModel);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ModelAndView("friend_list", "friends", friendships);
    }

    @RequestMapping(value = "/synchronize/friend", method = RequestMethod.GET)
    public ModelAndView getSynchronizedFriend(){
        friendshipService.synchronizeFriendship(ApplicationConstant.Twitter.SCREEN_NAME);
        List<Friendship> friendships = friendshipService.getAllFriendship();
        return new ModelAndView("friend_list", "friends", friendships);
    }
}
