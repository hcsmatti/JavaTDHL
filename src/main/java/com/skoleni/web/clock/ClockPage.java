package com.skoleni.web.clock;

import com.skoleni.web.BasePage;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public final class ClockPage extends BasePage {

    public ClockPage() {
        final Model model = new Model() {
            @Override
            public Serializable getObject() {
                SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
                String time = df.format(new Date());
                return time;
            }
        };
        add(new Label("time", model));
    }
//    public ClockPage() {
//        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
//        String time = df.format(new Date());
//        final Model model = new Model(time);
//        add(new Label("time", model));
//    }
}
