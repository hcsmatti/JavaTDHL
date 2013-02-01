package com.skoleni.web;

import com.skoleni.web.clock.ClockPage;
import com.skoleni.web.country.CountryDetailPage;
import com.skoleni.web.country.CountryListPage;
import com.skoleni.web.homepage.HomePage;
import com.skoleni.web.user.UserDetailPage;
import com.skoleni.web.user.UserListPage;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

@Component("skoleniApplication")
public class SkoleniApplication extends WebApplication {

    public SkoleniApplication() {
        mountBookmarkablePage("clock", ClockPage.class);
        mountBookmarkablePage("users", UserListPage.class);
        mountBookmarkablePage("user", UserDetailPage.class);

        mountBookmarkablePage("country", CountryDetailPage.class);
        mountBookmarkablePage("countries", CountryListPage.class);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();
        addComponentInstantiationListener(new SpringComponentInjector(this));
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new SkoleniSession(request);
    }
}