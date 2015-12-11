package com.zisal.twit.crawl.core;

import com.zisal.twit.crawl.core.model.Employee;
import com.zisal.twit.crawl.core.model.SecRole;
import com.zisal.twit.crawl.core.model.SecUser;
import com.zisal.twit.crawl.core.service.employee.IEmployeeService;
import com.zisal.twit.crawl.core.service.role.IRoleService;
import com.zisal.twit.crawl.core.service.user.IUserService;
import junit.framework.Assert;
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
public class TestSecurityRelationship {

    @InjectMocks
    @Autowired
    private IUserService userService;

    @InjectMocks
    @Autowired
    private IRoleService roleService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSetup(){
        SecRole secRole = new SecRole();
        secRole.setRoleName("ROLE_USER");

        SecUser secUser = new SecUser();
        secUser.setName("Fauzi");
        secUser.setSecRole(secRole);

        userService.save(secUser);
    }
}
