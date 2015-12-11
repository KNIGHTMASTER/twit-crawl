package com.zisal.twit.crawl.core.service.appconfiguration;

import com.zisal.twit.crawl.core.dao.IGenericDAO;
import com.zisal.twit.crawl.core.dao.appconfiguration.IAppConfigurationDAO;
import com.zisal.twit.crawl.core.service.GenericServiceJPAImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Ladies Man on 12/11/2015.
 */
@Service("appConfigurationService")
public class AppConfigurationServiceImpl extends GenericServiceJPAImpl implements IAppConfigurationService{

    IAppConfigurationDAO appConfigurationDAO;

    @Autowired
    public AppConfigurationServiceImpl(@Qualifier("appConfigurationDAO") IGenericDAO genericDAO) {
        super(genericDAO);
        this.appConfigurationDAO = (IAppConfigurationDAO) genericDAO;
    }
}
