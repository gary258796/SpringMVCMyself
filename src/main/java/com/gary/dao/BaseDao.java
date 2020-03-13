package com.gary.dao;

import java.util.List;


public interface BaseDao<T>{
    void save(T entity);
    T get(Class<?> clazz, Integer id);
    void update(T entity);
    void delete(T entity);
    void saveOrUpdate(T entity);
//    List<T> find(String queryName, String[] paramNames, Object[] values);
//    List<T> findByPage(final String hql, final int pageNo, final int pageSize);
//    List<T> findByPage(final String hql, final int pageNo, final int pageSize, String[] params, String[] values);

}
