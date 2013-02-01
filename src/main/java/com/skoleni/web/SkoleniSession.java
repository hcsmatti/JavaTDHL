package com.skoleni.web;

import com.skoleni.dao.entity.CountryEntity;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class SkoleniSession extends WebSession {

    private final List<CountryEntity> countries = new ArrayList<CountryEntity>();

    public SkoleniSession(Request request) {
        super(request);
        countries.add(new CountryEntity(1L, "CZ"));
        countries.add(new CountryEntity(2L, "SK"));
    }

    public List<CountryEntity> getCountries() {
        return countries;
    }

    public CountryEntity find(long id) {
        for (CountryEntity country : countries) {
            if (country.getId().equals(id)) {
                return country;
            }
        }
        throw new IllegalStateException("Country not found");
    }
}
