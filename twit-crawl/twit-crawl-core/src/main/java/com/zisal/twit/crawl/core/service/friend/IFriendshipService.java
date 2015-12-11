package com.zisal.twit.crawl.core.service.friend;

import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.IGenericService;

import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:13 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface IFriendshipService extends IGenericService {

    List<Friendship> getAllFriendship();

    List<Friendship> getAllFollowers();

    Friendship getFriendshipById(Integer id);

    Friendship getFriendshipByCode(String p_Code);

    void synchronizeFriendship(String screenName);

    Friendship getFirstFriendship();

    Friendship getNextFriendship(Friendship p_PrevFriendship);
}
