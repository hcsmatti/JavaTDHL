package com.skoleni.web;

import com.skoleni.dao.entity.CountryEntity;
import java.util.List;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public abstract class BasePage extends WebPage {

    protected List<CountryEntity> countries() {
        return ((SkoleniSession) getSession()).getCountries();
    }

    public BasePage() {
        super();
        add(HeaderContributor.forCss(BasePage.class, "style.css"));
//        InjectorHolder.getInjector().inject(this);
        
        add(new FeedbackPanel("feedback"));

    }
}