package com.zisal.twit.crawl.core.service.friend;

import com.zisal.twit.crawl.core.dao.IGenericDAO;
import com.zisal.twit.crawl.core.dao.friend.IFriendshipDAO;
import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.GenericServiceJPAImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:14 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Service("iFriendshipService")
public class FriendshipServiceImpl extends GenericServiceJPAImpl implements IFriendshipService {

    private IFriendshipDAO friendshipDAO;

    @Autowired
    public FriendshipServiceImpl(@Qualifier("friendshipDAO") IGenericDAO genericDAO) {
        super(genericDAO);
        this.friendshipDAO = (IFriendshipDAO) genericDAO;
    }

    @Override
    public List<Friendship> getAllFriendship() {
        return friendshipDAO.getAllFriendship();
    }

    @Override
    public List<Friendship> getAllFollowers() {
        return friendshipDAO.getAllFollowers();
    }

    @Override
    public void synchronizeFriendship(String screenName) {
        friendshipDAO.synchronizeFriendship(screenName);
    }
}
