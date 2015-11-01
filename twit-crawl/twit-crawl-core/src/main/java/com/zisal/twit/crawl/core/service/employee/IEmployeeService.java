package com.zisal.twit.crawl.core.service.employee;

import com.zisal.twit.crawl.core.model.Employee;
import com.zisal.twit.crawl.core.service.IGenericService;

import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:13 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface IEmployeeService extends IGenericService {

    List<Employee> getAllEmployee();

}
