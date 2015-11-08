package com.zisal.twit.crawl.core.dao.friend;

import com.zisal.twit.crawl.core.constant.ApplicationConstant;
import com.zisal.twit.crawl.core.crawler.ICrawler;
import com.zisal.twit.crawl.core.dao.GenericDAOJPAImpl;
import com.zisal.twit.crawl.core.dto.DTOFriendship;
import com.zisal.twit.crawl.core.model.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Repository("friendshipDAO")
public class FriendshipDAOImpl extends GenericDAOJPAImpl implements IFriendshipDAO {

    @Autowired
    @Qualifier(value = "crawler")
    ICrawler crawler;

    @Override
    public List<Friendship> getAllFriendship() {
        Query query = entityManager.createQuery("select a from friendship a ");
        List<Friendship> result = query.getResultList();
        return result;
    }

    @Override
    public List<Friendship> getAllFollowers() {
        Query query = entityManager.createQuery("select a from friendship a where a.type =:friendshipType");
        query.setParameter("friendshipType", 1);
        List<Friendship> result = query.getResultList();
        return result;
    }

    @Override
    public void synchronizeFriendship(String screenName) {
        deleteAllEntities(Friendship.class);
        List<DTOFriendship> dtoFriendships = crawler.getFriends(screenName);
        try{
            for(DTOFriendship friendshipDto : dtoFriendships){
                Friendship friendship = new Friendship();
                friendship.setCode(friendship.getCode());
                friendship.setName(friendshipDto.getName());
                friendship.setImageUrl(friendshipDto.getImageUrl());
                save(friendship);
            }
        }catch (Exception e){
            logger.error(ApplicationConstant.LOG.ZUNA_ERROR, e.getLocalizedMessage());
        }

    }
}
