package com.zisal.twit.crawl.core.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Achmad Fauzi on 9/14/2015 : 5:40 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 * @param <DATA>
 * @param <KEY>
 */
public interface IGenericService<DATA, KEY> {

    @Transactional
    void save(DATA data);

    @Transactional
    void merge(DATA data);

    DATA read(Class<DATA> dataClass, KEY id);

    @Transactional
    void delete(DATA data);

    @Transactional
    void deleteById(Class<DATA> dataClass, KEY id);
}
