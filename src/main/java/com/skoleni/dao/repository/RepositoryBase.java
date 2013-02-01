package com.skoleni.dao.repository;

import com.skoleni.dao.entity.UserEntity;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class RepositoryBase<T extends Serializable> implements Repository<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public final void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public final T get(Class<T> clazz, long id) {
        return (T) getSession().get(clazz, id);
    }

    @Transactional
    @Override
    public final void delete(T entity) {
        getSession().delete(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public final List<T> list(Class<T> clazz) {
        final Criteria criteria = getSession().createCriteria(clazz);
        return criteria.list();
    }
    
}