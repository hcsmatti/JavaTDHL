package com.skoleni.web.user;

import com.skoleni.dao.entity.UserEntity;
import com.skoleni.dao.repository.UserRepository;
import com.skoleni.web.BasePage;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;


public final class UserListPage extends BasePage {

    @SpringBean
    private UserRepository repository;

    public UserListPage() {
        super();
        
        List<IColumn<UserEntity>> columns = new
	ArrayList<IColumn<UserEntity>>();
	columns.add(new PropertyColumn<UserEntity>(Model.of("Id"),
	"id","id"));
	columns.add(new PropertyColumn<UserEntity>(Model.of("First name"),
	"firstName", "firstName"));
	columns.add(new PropertyColumn<UserEntity>(Model.of("Last name"),
	"lastName", "lastName"));
        
        
        
        
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
