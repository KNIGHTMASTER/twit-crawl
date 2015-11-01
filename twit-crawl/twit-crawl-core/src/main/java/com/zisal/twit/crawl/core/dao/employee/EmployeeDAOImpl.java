package com.zisal.twit.crawl.core.dao.employee;


import com.zisal.twit.crawl.core.dao.GenericDAOJPAImpl;
import com.zisal.twit.crawl.core.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Repository("employeeDAO")
public class EmployeeDAOImpl extends GenericDAOJPAImpl implements IEmployeeDAO {

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> result;
        Query query = entityManager.createQuery("select a from employee a");
        result = query.getResultList();
        return result;
    }
}
