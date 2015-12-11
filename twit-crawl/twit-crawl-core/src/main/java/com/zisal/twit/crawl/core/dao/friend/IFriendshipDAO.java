package com.zisal.twit.crawl.core.dao.friend;


import com.zisal.twit.crawl.core.model.Friendship;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:10 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface IFriendshipDAO {

    List<Friendship> getAllFriendship();

    List<Friendship> getAllFollowers();

    @Transactional
    void synchronizeFriendship(String screenName);

    Friendship getFriendshipById(Integer id);

    Friendship getFriendshipByCode(String p_Code);

    Friendship findFirstFriendship();

    Friendship getNextFriendship(Friendship p_PrevFriendship);
}
