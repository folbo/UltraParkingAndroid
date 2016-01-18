package net.silver.ultra.ultraandroid.Authentication.rest;

import net.silver.ultra.ultraandroid.Authentication.model.LoginParams;
import net.silver.ultra.ultraandroid.Authentication.model.LoginResponse;
import net.silver.ultra.ultraandroid.MyApp;
import net.silver.ultra.ultraandroid.R;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.SetsCookie;
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
    @SetsCookie({".AspNet.ApplicationCookie"})
    LoginResponse login( @Body LoginParams params);

    @Get("/test/authtest")
    @RequiresCookie({".AspNet.ApplicationCookie"})
    String testPass();

    String getCookie(String name);
    void setCookie(String name, String value);
}
