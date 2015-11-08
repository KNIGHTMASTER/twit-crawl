package com.zisal.twit.crawl.core;

import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.friend.IFriendshipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void doSetup(){
        List<Friendship> friendships;
        try{
            friendships = iFriendshipService.getAllFriendship();
            for(Friendship friendship : friendships){
                System.out.println(friendship.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Friendship friendship = new Friendship();
        friendship.setCode("1");
        friendship.setName("1");
        friendship.setImageUrl("1");
        friendship.setType(1);
        iFriendshipService.save(friendship);

        iFriendshipService.deleteAllEntities(Friendship.class);
    }
}
