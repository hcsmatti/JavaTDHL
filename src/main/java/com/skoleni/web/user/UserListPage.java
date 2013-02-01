package com.skoleni.web.user;

import com.skoleni.dao.entity.CountryEntity;
import com.skoleni.dao.entity.UserEntity;
import com.skoleni.dao.repository.UserRepository;
import com.skoleni.web.BasePage;
import java.util.Arrays;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public final class UserListPage extends BasePage {

    @SpringBean
    private UserRepository repository;

    public UserListPage() {
        super();
        WebMarkupContainer datacontainer = new WebMarkupContainer("data");
        datacontainer.setOutputMarkupId(true);
        add(datacontainer);

        PageableListView listview = new PageableListView("rows", repository.list(UserEntity.class), 10) {
            @Override
            protected void populateItem(ListItem item) {
                final UserEntity user = (UserEntity) item.getModelObject();
                item.add(new Label("id", Long.toString(user.getId())));
                item.add(new Label("firstName", user.getFirstName()));
                item.add(new Label("lastName", user.getLastName()));
            }
        };

        datacontainer.add(listview);
        datacontainer.add(new AjaxPagingNavigator("navigator", listview));
        datacontainer.setVersioned(false);
    }

    public UserListPage(PageParameters params) {
        //TODO:  process page parameters
    }
}
