package com.skoleni.web.user;

import com.skoleni.dao.entity.UserEntity;
import com.skoleni.dao.repository.UserRepository;
import com.skoleni.web.BasePage;
import com.skoleni.web.country.CountryListPage;
import com.skoleni.web.country.CountryPanel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

public final class UserDetailPage extends BasePage {

    @SpringBean
    private UserRepository repository;

    private Form<UserEntity> form;

    public UserDetailPage() {
        initPage(null);
    }

    public UserDetailPage(Long id) {
        initPage(id);
    }

    public UserDetailPage(PageParameters params) {
        final long id = params.getLong("id");
        initPage(id);
    }

    private void initPage(final Long id) {
        
        setFormModel(id);

        final WebMarkupContainer idContainer = new WebMarkupContainer("idContainer") {
            @Override
            public boolean isVisible() {
                return (form.getModelObject().getId() != null);
            }
        };
        form.add(idContainer);

        idContainer.add(new Label("idLabel", new ResourceModel("UserEntity.id")));
        idContainer.add(new TextField("id") {
            @Override
            public boolean isEnabled() {
                return false;
            }
        });

        form.add(new Label("firstNameLabel", new ResourceModel("UserEntity.firstName")));
        form.add(new TextField("firstName") {
            @Override
            public boolean isRequired() {
                return true;
            }
        }.add(new StringValidator.MinimumLengthValidator(3)));

        form.add(new Label("lastNameLabel", "Příjmeni"));
        form.add(new TextField("lastName"));

        form.add(new Label("systemUserLabel", "Systémový uživatel"));
        final CheckBox systemUserCheckBox = new CheckBox("systemUser");
        form.add(systemUserCheckBox);

        final WebMarkupContainer passwordContainer = new WebMarkupContainer("passwordContainer") {
            @Override
            public boolean isVisible() {
                return !form.getModelObject().isSystemUser();
            }
        };
        passwordContainer.setOutputMarkupPlaceholderTag(true);
        form.add(passwordContainer);

        passwordContainer.add(new Label("passwordLabel", "Heslo"));
        passwordContainer.add(new PasswordTextField("password") {
            @Override
            public boolean isRequired() {
                return !form.getModelObject().isSystemUser();
            }
        }.setResetPassword(true));

        systemUserCheckBox.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent(passwordContainer);
            }
        });

        form.add(new CountryPanel("countryPanel"));

        form.add(new Button("submitButton"));
    }

    private void setFormModel(Long id) {
        UserEntity user;
        if (id == null) {
            user = new UserEntity();
        } else {
            user = repository.get(UserEntity.class, id);
        }

        if (user == null) {
            throw new IllegalArgumentException("Country not found!!!");
        }

        add(form = new Form("form", new CompoundPropertyModel<UserEntity>(user)) {
            @Override
            protected void onSubmit() {
                final UserEntity user = form.getModelObject();
                repository.saveOrUpdate(user);
                getSession().info("User saved");
                setResponsePage(new UserListPage());
            }
        });
    }
}
