package net.silver.ultra.ultraandroid.transaction.rest;

import net.silver.ultra.ultraandroid.transaction.model.TransactionModel;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by folbo on 2016-01-21.
 */

@Rest(rootUrl = "http://ultra.dev/api",converters = {MappingJackson2HttpMessageConverter.class})
public interface TransactionRestService {

    @Get("/transactions/all")
    @RequiresCookie({".AspNet.ApplicationCookie"})
    TransactionModel[] getAll();

    void setCookie(String name, String value);
}
