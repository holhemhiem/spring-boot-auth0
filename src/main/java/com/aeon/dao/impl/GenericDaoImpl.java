/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.dao.impl;

import com.aeon.dao.GenericDao;
import com.aeon.exception.NoDataException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmacaraeg
 */
@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    
    @Autowired
    SessionFactory sessionFactory;
    
    @Override
    public List<T> getAll(Class<T> entity) throws NoDataException {
        List<T> list = sessionFactory.getCurrentSession()
                .createCriteria(entity)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        
        if(list != null && list.size() > 0) {
            return list;
        } else {
            throw new NoDataException("No data from table :: " + entity.getSimpleName());
        }
    }

    @Override
    public T get(Class<T> entity, int id) throws NoDataException {
        T t = (T) sessionFactory.getCurrentSession().get(entity, id);
        
        if(t != null) {
            return t;
        } else {
            throw new NoDataException("No data from table :: " + entity.getSimpleName());
        }
    }
    
    @Override
    public List<T> getByCriteria(Class<T> entity, List<Criterion> criterions) throws NoDataException {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(entity);
        
        criterions.stream().forEach((criterion) -> {
            criteria.add(criterion);
        });
        
        List<T> list = criteria.list();
        
        if(list != null && list.size() > 0) {
            return list;
        } else {
            throw new NoDataException("No data from table :: " + entity.getSimpleName());
        }
    }

    @Override
    public T saveOrUpdate(T t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
        return t;
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(id);
    }
}
