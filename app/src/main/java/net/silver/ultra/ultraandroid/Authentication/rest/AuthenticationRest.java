package net.silver.ultra.ultraandroid.Authentication.rest;

import net.silver.ultra.ultraandroid.Authentication.model.LoginParams;
import net.silver.ultra.ultraandroid.Authentication.model.LoginResponse;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import retrofit2.http.Body;


/**
 * @author aturski
 */
@Rest(rootUrl = "http://ultra.dev/api" ,converters = { MappingJackson2HttpMessageConverter.class })
public interface AuthenticationRest extends RestClientRootUrl, RestClientErrorHandling  {
    @Post("/account/login")
    LoginResponse login( @Body LoginParams params);
}
