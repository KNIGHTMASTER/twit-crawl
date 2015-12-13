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
            logger.error(ApplicationConstant.LogTag.ZUNA_ERROR, e.getLocalizedMessage());
        }

    }

    @Override
    public Friendship getFriendshipById(Integer id){
        Query query = entityManager.createQuery("select a from friendship a where a.id =:friendshipId");
        query.setParameter("friendshipId", id);
        return (Friendship) query.getSingleResult();
    }

    @Override
    public Friendship getFriendshipByCodeAndLowestLevel(String p_Code, Integer p_LowestLevel) {
        Query query = entityManager.createQuery("select a from friendship a where a.code =:friendshipCode and a.level =:lowestLevel");
        query.setParameter("friendshipCode", p_Code);
        query.setParameter("lowestLevel", p_LowestLevel);
        return (Friendship) query.getSingleResult();
    }

    @Override
    public Friendship findFirstFriendship() {
        Query query = entityManager.createQuery("select a from friendship a where a.id = (" +
                "select min(b.id) from friendship b ) ");
        return (Friendship) query.getSingleResult();
    }

    @Override
    public Friendship getNextFriendship(Friendship p_PrevFriendship) {
        Query query = entityManager.createQuery(
                "select a from friendship a where a.id = " +
                        "(select min(b.id) from friendship b where b.id > :friendshipId)"
        );
        query.setParameter("friendshipId", p_PrevFriendship.getId());
        return (Friendship) query.getSingleResult();
    }

    @Override
    public Integer getLowestLevelOfFriendship() {
        Query query = entityManager.createQuery("select max(a.level) from friendship a", Integer.class);
        return (Integer) query.getSingleResult();
    }
}
