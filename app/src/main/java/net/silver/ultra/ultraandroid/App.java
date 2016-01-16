package net.silver.ultra.ultraandroid;

import android.app.Application;

import net.silver.ultra.ultraandroid.rest.AuthenticationRest;
import net.silver.ultra.ultraandroid.rest.ErrorHandler;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestErrorHandler;

/**
 * Created by Sylwekqaz on 16.01.2016.
 */
@EApplication
public class App extends Application {

    @RestService
    AuthenticationRest authenticationRest;

    @Bean
    ErrorHandler restErrorHandler;

    @AfterInject
    void init(){
        authenticationRest.setRestErrorHandler(restErrorHandler);
    }
}
