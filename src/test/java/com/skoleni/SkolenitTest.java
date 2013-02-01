package com.skoleni;

import com.skoleni.dao.entity.CountryEntity;
import com.skoleni.dao.entity.RoleEntity;
import com.skoleni.dao.entity.UserEntity;
import com.skoleni.dao.repository.CountryRepository;
import com.skoleni.dao.repository.RoleRepository;
import com.skoleni.dao.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ContextConfiguration(locations = {"classpath:**/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class SkolenitTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RoleRepository roleRepository;

//    private 
    @Test
    public void testSaveCountries() {
        CountryEntity country = new CountryEntity();
        country.setName("Česká republika");
        countryRepository.saveOrUpdate(country);

        country = new CountryEntity();
        country.setName("Slovenská republika");
        countryRepository.saveOrUpdate(country);
    }

    @Test
    public void testSave() {
        final UserEntity user = new UserEntity();
        user.setFirstName("Ferda");
        user.setLastName("Mravenec");
        user.setCountry(countryRepository.get(CountryEntity.class, 1L));
        userRepository.saveOrUpdate(user);
    }

    @Test
    public void testGet() {
        final UserEntity user = userRepository.get(UserEntity.class, 1L);
        Assert.assertNotNull(user);
    }

    @Test
    public void testList() {
        final List<UserEntity> list = userRepository.list(UserEntity.class);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testCzechUsers() {
        final CountryEntity country = countryRepository.get(CountryEntity.class, 1L);
        List<UserEntity> users = userRepository.listFromCountry(country);
        Assert.assertEquals(1L, users.size());
    }

    @Test
    public void testCreateRoles() {
        RoleEntity role = new RoleEntity();
        role.setName("Admin");
        roleRepository.saveOrUpdate(role);

        role = new RoleEntity();
        role.setName("Uživatel");
        roleRepository.saveOrUpdate(role);
    }

    @Test
    public void testAssignRole() {
        final UserEntity user = userRepository.get(UserEntity.class, 1L);
        user.setRoles(new HashSet<RoleEntity>(roleRepository.list(RoleEntity.class)));
        userRepository.saveOrUpdate(user);
    }

    @Test
    @Ignore
    public void testDelete() {
        userRepository.delete(userRepository.get(UserEntity.class, 1L));
    }
}
