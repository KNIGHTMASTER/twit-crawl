package com.zisal.twit.crawl.core.crawler;

import com.zisal.twit.crawl.core.dto.DTOFriendship;


import java.util.List;

/**
 * Created by fauzi on 11/2/15.
 */
public interface ICrawler {

    List<DTOFriendship> getFollowers(String screenName);

    List<DTOFriendship> getFriends(String screenName);
}
