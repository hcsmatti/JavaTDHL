package com.skoleni.dao.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends Serializable> {

    void delete(T entity);

    T get(Class<T> clazz, long id);

    List<T> list(Class<T> clazz);

    void saveOrUpdate(T entity);
}
