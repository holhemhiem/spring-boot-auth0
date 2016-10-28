/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.dao;

import com.aeon.exception.NoDataException;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author jmacaraeg
 */
public interface GenericDao<T> {
    List<T> getAll(Class<T> entity) throws NoDataException;
    T get(Class<T> entity, int id) throws NoDataException;
    List<T> getByCriteria(Class<T> entity, List<Criterion> criterions) throws NoDataException;
    T saveOrUpdate(T t);
    void delete(int id);
}
