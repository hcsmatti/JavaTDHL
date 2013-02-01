package com.skoleni.web.homepage;

import com.skoleni.web.BasePage;
import com.skoleni.web.clock.ClockPage;
import com.skoleni.web.user.UserDetailPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

public class HomePage extends BasePage {

    public HomePage() {
        add(new Label("message", "Hello, World!!!"));

        add(new Link("clockButton") {
            @Override
            public void onClick() {
                setResponsePage(new ClockPage());
            }

            @Override
            public boolean isVisible() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        });

        add(new Link("userButton") {
            @Override
            public void onClick() {
                setResponsePage(new UserDetailPage());
            }
        });

    }
}