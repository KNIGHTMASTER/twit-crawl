package com.zisal.twit.crawl.core.service;

import com.zisal.twit.crawl.core.dao.IGenericDAO;
import org.springframework.stereotype.Service;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 5:48 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 * @param <KEY>
 */
@Service
public class GenericServiceJPAImpl<DATA, KEY> implements IGenericService<DATA, KEY> {

    private IGenericDAO genericDAO;

    public GenericServiceJPAImpl() {
    }

    public GenericServiceJPAImpl(IGenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public void save(DATA data) {
        genericDAO.save(data);
    }

    @Override
    public void merge(DATA data) {
        genericDAO.merge(data);
    }

    @Override
    public DATA read(Class<DATA> dataClass, KEY id) {
        return (DATA) genericDAO.read(dataClass, id);
    }

    @Override
    public void delete(DATA data) {
        genericDAO.delete(data);
    }

    @Override
    public void deleteById(Class<DATA> dataClass, KEY id) {
        delete(read(dataClass, id));
    }

    @Override
    public void deleteAllEntities(Class<DATA> entityType) {
        genericDAO.deleteAllEntities(entityType);
    }
}
