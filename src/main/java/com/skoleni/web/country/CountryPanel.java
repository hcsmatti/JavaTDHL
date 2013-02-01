package com.skoleni.web.country;

import com.skoleni.dao.entity.CountryEntity;
import com.skoleni.dao.repository.CountryRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public final class CountryPanel extends Panel {

    @SpringBean
    private CountryRepository repository;

    public CountryPanel(String id) {
        super(id);
        add(new Label("countryLabel", new ResourceModel("UserEntity.country")));
        add(new DropDownChoice<CountryEntity>("country", repository.list(CountryEntity.class), new ChoiceRenderer("name", "id")) {
            @Override
            public boolean isNullValid() {
                return false;
            }
        });
    }
}