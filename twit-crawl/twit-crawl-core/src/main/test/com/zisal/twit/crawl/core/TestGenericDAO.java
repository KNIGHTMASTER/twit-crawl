package com.zisal.twit.crawl.core;

import com.zisal.twit.crawl.core.model.Employee;
import com.zisal.twit.crawl.core.service.employee.IEmployeeService;
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

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doSetup(){
        List<Employee> employees = null;
        try {
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
}
