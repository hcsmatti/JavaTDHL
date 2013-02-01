package com.skoleni.dao.repository;

import com.skoleni.dao.entity.CountryEntity;
import com.skoleni.dao.entity.UserEntity;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends RepositoryBase<UserEntity> implements UserRepository {

    public List<UserEntity> listFromCountry(CountryEntity country) {
        final Query query = getSession().createQuery("from UserEntity u where u.country.name = :name");
        query.setParameter("name", country.getName());
        return query.list();
    }
}