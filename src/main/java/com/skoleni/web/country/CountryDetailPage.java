package com.skoleni.web.country;

import com.skoleni.dao.entity.CountryEntity;
import com.skoleni.dao.repository.CountryRepository;
import com.skoleni.web.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

public final class CountryDetailPage extends BasePage {

    @SpringBean
    private CountryRepository repository;
    private Form<CountryEntity> form;

    public CountryDetailPage(Long id) {
        initPage(id);
    }

    public CountryDetailPage(PageParameters params) {
        final long id = params.getLong("id");
        initPage(id);
    }

    public CountryDetailPage() {
        initPage(null);
    }

    private void initPage(Long id) {
        
        setFormModel(id);

        final WebMarkupContainer idContainer = new WebMarkupContainer("idContainer") {
            @Override
            public boolean isVisible() {
                return (form.getModelObject().getId() != null);
            }
        };
        form.add(idContainer);

        idContainer.add(new Label("idLabel", "Id"));
        idContainer.add(new TextField("id") {
            @Override
            public boolean isEnabled() {
                return false;
            }
        });


        form.add(new Label("nameLabel", "Name"));
        form.add(new TextField("name") {
            @Override
            public boolean isRequired() {
                return true;
            }
        }.add(new StringValidator.MinimumLengthValidator(2)));


        form.add(new Button("submitButton") {
            @Override
            public void onSubmit() {
                super.onSubmit();
            }
        });
    }

    private void setFormModel(final Long id) {
    add(form = new Form("form", new CompoundPropertyModel<CountryEntity>(new LoadableDetachableModel<CountryEntity>() {
            @Override
            protected CountryEntity load() {
                CountryEntity country;
                if (id == null) {
                    country = new CountryEntity();
                } else {
                    country = repository.get(CountryEntity.class, id);
                }

                if (country == null) {
                    throw new IllegalArgumentException("Country not found!!!");
                }
                return country;
            }
        })) {
            @Override
            protected void onSubmit() {
                final CountryEntity country = form.getModelObject();
                repository.saveOrUpdate(country);
                setResponsePage(new CountryListPage());
            }
        });
    }
}
