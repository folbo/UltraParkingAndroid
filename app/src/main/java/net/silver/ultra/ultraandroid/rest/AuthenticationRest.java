package net.silver.ultra.ultraandroid.rest;

import net.silver.ultra.ultraandroid.rest.model.LoginParams;
import net.silver.ultra.ultraandroid.rest.model.LoginResponse;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import retrofit2.http.Body;

/**
 * Created by Sylwekqaz on 15.01.2016.
 */

@Rest(rootUrl = "http://192.168.56.1:4665/api" ,converters = { MappingJackson2HttpMessageConverter.class })
public interface AuthenticationRest extends RestClientErrorHandling {
        @Post("/account/login")
        LoginResponse login(@Body LoginParams params);
}
