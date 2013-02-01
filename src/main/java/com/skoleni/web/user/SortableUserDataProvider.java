/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skoleni.web.user;

import com.skoleni.dao.entity.UserEntity;
import com.skoleni.dao.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Administrator
 */
public class SortableUserDataProvider extends SortableDataProvider<UserEntity> {

    @SpringBean
    private UserRepository repository;

    public SortableUserDataProvider() {
        setSort("lastName", true);
    }

    public Iterator<? extends UserEntity> iterator(int first, int count) {
        List<UserEntity> data = new ArrayList<UserEntity>();
        Collections.sort(data, new Comparator<UserEntity>() {
            public int compare(UserEntity o1, UserEntity o2) {
                int dir = getSort().isAscending() ? 1 : -1;
                if ("lastName".equals(getSort().getProperty())) {
                    return dir * (o1.getLastName().compareTo(o2.getLastName()));
                } else {
                    return dir * ((int) (o1.getId() - o2.getId()));
                }
            }
        });
        return data.subList(first,
                Math.min(first + count, data.size())).iterator();
    }

    public int size() {
        return repository.getSize();
    }

    public IModel<UserEntity> model(UserEntity object) {
        return Model.of(object);
    }

    public IModel<UserEntity> model(UserEntity t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
