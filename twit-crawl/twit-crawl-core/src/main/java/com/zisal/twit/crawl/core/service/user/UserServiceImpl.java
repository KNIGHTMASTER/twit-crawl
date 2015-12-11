package com.zisal.twit.crawl.core.service.user;

import com.zisal.twit.crawl.core.dao.IGenericDAO;
import com.zisal.twit.crawl.core.dao.role.IRoleDAO;
import com.zisal.twit.crawl.core.dao.user.IUserDAO;
import com.zisal.twit.crawl.core.service.GenericServiceJPAImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:14 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Service("iUserService")
public class UserServiceImpl extends GenericServiceJPAImpl implements IUserService {

    private IUserDAO userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") IGenericDAO genericDAO) {
        super(genericDAO);
        this.userDAO = (IUserDAO) genericDAO;
    }
    
}
