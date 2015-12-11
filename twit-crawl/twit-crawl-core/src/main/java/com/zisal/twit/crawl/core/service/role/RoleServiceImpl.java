package com.zisal.twit.crawl.core.service.role;

import com.zisal.twit.crawl.core.dao.IGenericDAO;
import com.zisal.twit.crawl.core.dao.friend.IFriendshipDAO;
import com.zisal.twit.crawl.core.dao.role.IRoleDAO;
import com.zisal.twit.crawl.core.model.Friendship;
import com.zisal.twit.crawl.core.service.GenericServiceJPAImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 6:14 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@Service("iRoleService")
public class RoleServiceImpl extends GenericServiceJPAImpl implements IRoleService {

    private IRoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(@Qualifier("roleDAO") IGenericDAO genericDAO) {
        super(genericDAO);
        this.roleDAO = (IRoleDAO) genericDAO;
    }

}
