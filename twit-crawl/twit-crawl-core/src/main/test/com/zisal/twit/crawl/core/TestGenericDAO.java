package com.zisal.twit.crawl.core;

import com.zisal.twit.crawl.core.model.AppConfiguration;
import com.zisal.twit.crawl.core.model.Employee;
import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.appconfiguration.IAppConfigurationService;
import com.zisal.twit.crawl.core.service.employee.IEmployeeService;
import com.zisal.twit.crawl.core.service.friend.IFriendshipService;
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
public class TestGenericDAO {

    @InjectMocks
    @Autowired
    private IEmployeeService iEmployeeService;

    @InjectMocks
    @Autowired
    private IAppConfigurationService iAppConfigurationService;

    @InjectMocks
    @Autowired
    private IFriendshipService friendshipService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    public void doSetup(){
        List<Employee> employees = null;
        try {
            Employee em = new Employee();
            em.setEmployeeName("sample");
            em.setEmployeeAddress("sample");
            iEmployeeService.save(em);
            employees = iEmployeeService.getAllEmployee();
            for(Employee employee : employees){
                System.out.println(employee.toString());
            }
            Assert.assertNotNull(employees);
            Employee e = employees.get(0);
            System.out.println("EMPLOYEE 1" + e.toString());

            try{
                Employee newEmployee = (Employee) iEmployeeService.read(Employee.class, e.getId());
                System.out.println("READ employee "+newEmployee.toString());
            }catch (Exception e1){
                e1.printStackTrace();
            }

            e.setEmployeeName("XXX");
            e.setEmployeeAddress("YYY");
            iEmployeeService.merge(e);
            Employee mergedEmployee = (Employee) iEmployeeService.read(Employee.class, e.getId());
            System.out.println("Merged employee "+mergedEmployee.toString());
            Assert.assertNotNull(e);


            iEmployeeService.delete(e);
            Employee deletedEmployee = null;
            try{
                deletedEmployee = (Employee) iEmployeeService.read(Employee.class, e);
            }catch (Exception ex){
                Assert.assertNull(deletedEmployee);
            }
        }catch (Exception e){
            System.out.println(e.toString());
            //Assert.assertEquals(0, employees.size());
        }
    }

    public void doTestAppConfig(){
        List<AppConfiguration> appConfigurationList =
                iAppConfigurationService.loadAllEntity(AppConfiguration.class);
        for(AppConfiguration appConfiguration: appConfigurationList){
            System.out.println(appConfiguration.toString());
        }
        Assert.assertEquals(1, appConfigurationList.size());
    }

    @Test
    public void doTestNextRecord(){
        Friendship prevFriendship = friendshipService.getFirstFriendship();
        Friendship nextFriendship = friendshipService.getNextFriendship(prevFriendship);
        System.out.println(nextFriendship.toString());
    }
}
