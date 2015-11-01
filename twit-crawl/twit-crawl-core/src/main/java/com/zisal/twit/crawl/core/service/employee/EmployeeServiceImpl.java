package com.zisal.twit.crawl.core.service.employee;

import com.zisal.twit.crawl.core.dao.IGenericDAO;
import com.zisal.twit.crawl.core.dao.employee.IEmployeeDAO;
import com.zisal.twit.crawl.core.model.Employee;
import com.zisal.twit.crawl.core.service.GenericServiceJPAImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:14 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Service("iEmployeeService")
public class EmployeeServiceImpl extends GenericServiceJPAImpl implements IEmployeeService {

    private IEmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeDAO") IGenericDAO genericDAO) {
        super(genericDAO);
        this.employeeDAO = (IEmployeeDAO) genericDAO;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }
}
