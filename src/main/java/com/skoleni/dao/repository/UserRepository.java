package com.skoleni.dao.repository;

import com.skoleni.dao.entity.CountryEntity;
import com.skoleni.dao.entity.UserEntity;
import java.util.List;

public interface UserRepository extends Repository<UserEntity> {

    List<UserEntity> listFromCountry(CountryEntity country);
    
    

 }
