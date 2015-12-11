package com.zisal.twit.crawl.core;

import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.friend.IFriendshipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Fauzi on 9/15/2015 : 5:10 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = "classpath:/META-INF/spring/ApplicationModule.xml"
)
public class TestFriendshipDAO {

    @InjectMocks
    @Autowired
    private IFriendshipService iFriendshipService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    Logger logger = LoggerFactory.getLogger(TestFriendshipDAO.class);

    @Test
    public void doSetup(){
        Friendship andi = new Friendship();
        andi.setName("andi");
        andi.setCode("andi");
        andi.setType(1);
        andi.setLevel(1);

        Friendship ani = new Friendship();
        ani.setName("ani");
        ani.setCode("ani");
        ani.setLevel(1);
        ani.setType(1);

        Friendship budi = new Friendship();
        budi.setName("budi");
        budi.setCode("budi");
        budi.setLevel(1);
        budi.setType(1);

        Friendship siti = new Friendship();
        siti.setName("siti");
        siti.setCode("siti");
        siti.setLevel(1);
        siti.setType(1);

        /*ANDI - ANI - BUDI - SITI*/

        /*Andi has friend budi and siti*/
        andi.getFriendships().add(budi);
        andi.getFriendships().add(siti);

        /*Ani has friend only siti*/
        ani.getFriendships().add(siti);

        budi.getFriendships().add(siti);

        siti.getFriendships().add(andi);
        siti.getFriendships().add(budi);

        List<Friendship> friendships = new ArrayList<>();
        friendships.add(andi);
        friendships.add(ani);
        friendships.add(budi);
        friendships.add(siti);

        for(Friendship friendship : friendships){
            logger.info("FRIENDSHIP : ", friendship.getCode());
            try{
                iFriendshipService.save(friendship);
            }catch (Exception e){
                iFriendshipService.merge(friendship);
            }
        }
    }
}
