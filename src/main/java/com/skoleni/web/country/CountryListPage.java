package com.skoleni.web.country;

import com.skoleni.dao.entity.CountryEntity;
import com.skoleni.dao.repository.CountryRepository;
import com.skoleni.web.BasePage;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public final class CountryListPage extends BasePage {

    @SpringBean
    private CountryRepository repository;

    public CountryListPage() {
        super();

        WebMarkupContainer datacontainer = new WebMarkupContainer("data");
        datacontainer.setOutputMarkupId(true);
        add(datacontainer);

        PageableListView listview = new PageableListView("rows", repository.list(CountryEntity.class), 2) {
            @Override
            protected void populateItem(ListItem item) {
                final CountryEntity country = (CountryEntity) item.getModelObject();
                item.add(new Label("id", Long.toString(country.getId())));
                item.add(new Label("name", country.getName()));
            }
        };

        datacontainer.add(listview);
        datacontainer.add(new AjaxPagingNavigator("navigator", listview));
        datacontainer.setVersioned(false);
    }
}