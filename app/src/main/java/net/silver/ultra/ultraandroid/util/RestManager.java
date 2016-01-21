package net.silver.ultra.ultraandroid.util;

import android.os.Handler;
import android.widget.Toast;

import net.silver.ultra.ultraandroid.AppPrefs_;
import net.silver.ultra.ultraandroid.Authentication.errorhandlers.AuthenticationServiceErrorHandler;
import net.silver.ultra.ultraandroid.Authentication.event.UserLoggedIn;
import net.silver.ultra.ultraandroid.Authentication.event.UserLoggedOut;
import net.silver.ultra.ultraandroid.Authentication.model.LoginParams;
import net.silver.ultra.ultraandroid.Authentication.model.LoginResponse;
import net.silver.ultra.ultraandroid.Authentication.rest.AuthenticationRest;
import net.silver.ultra.ultraandroid.MyApp;
import net.silver.ultra.ultraandroid.R;
import net.silver.ultra.ultraandroid.googledirections.GoogleDirectionsRestService;
import net.silver.ultra.ultraandroid.parking.errorhandlers.ParkingServiceErrorHandler;
import net.silver.ultra.ultraandroid.parking.rest.ParkingRestService;
import net.silver.ultra.ultraandroid.transaction.errorhandlers.TransactionServiceErrorHandler;
import net.silver.ultra.ultraandroid.transaction.rest.TransactionRestService;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Sylwekqaz on 17.01.2016.
 */

@EBean(scope = EBean.Scope.Singleton)
public class RestManager {
    @StringRes(R.string.auth_cookie)
    String cookieName;

    @App
    MyApp app;

    @Pref
    AppPrefs_ prefs;
    @Bean
    ParkingServiceErrorHandler parkingServiceErrorHandler;
    @Bean
    AuthenticationServiceErrorHandler authServiceErrorHandler;
    @Bean
    TransactionServiceErrorHandler transactionServiceErrorHandler;

    @RestService
    protected AuthenticationRest authenticationRest;
    @RestService
    ParkingRestService parkingRestService;
    @RestService
    GoogleDirectionsRestService directionsRestService;
    @RestService
    TransactionRestService transactionRestService;

    @AfterInject
    void init(){
        parkingRestService.setRestErrorHandler(parkingServiceErrorHandler);
        authenticationRest.setRestErrorHandler(authServiceErrorHandler);
        transactionRestService.setRestErrorHandler(transactionServiceErrorHandler);

        injectAuthCookie();
    }

    private void injectAuthCookie() {
        String cookieval = prefs.GetAuthCookieValue().getOr("");
        authenticationRest.setCookie(cookieName, cookieval);
        parkingRestService.setCookie(cookieName, cookieval);
        transactionRestService.setCookie(cookieName, cookieval);
    }

    public AuthenticationRest getAuthenticationRest() {
        return authenticationRest;
    }

    public ParkingRestService getParkingRestService() { return parkingRestService; }

    public GoogleDirectionsRestService getDirectionsRestService() { return directionsRestService; }

    public TransactionRestService getTransactionRestService() { return transactionRestService; }

    public boolean Login(LoginParams params) {
        LoginResponse response = authenticationRest.login(params);

        //server error - wrong model
        if(response == null) return false;

        //wrong username or password
        if(response.getStatus().equals("Failure")) {
            Handler mainThreadHandler = new Handler(app.getMainLooper());
            Runnable showMessage = new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(app, "Niepoprawny login/hasło", Toast.LENGTH_SHORT);
                    toast.show();
                }
            };
            mainThreadHandler.post(showMessage);
            return false;
        }

        String cookie = authenticationRest.getCookie(cookieName);
        prefs.edit().GetAuthCookieValue().put(cookie).apply();
        prefs.edit().GetUserEmail().put(params.getEmail()).apply();
        injectAuthCookie();

        app.getBus().post(new UserLoggedIn(response.getUserId(), params.getEmail()));

        return true;
    }

    public void Logout() {
        prefs.edit().GetAuthCookieValue().put("").apply();
        prefs.edit().GetUserEmail().put("Niezalogowany").apply();
        injectAuthCookie();

        app.getBus().post(new UserLoggedOut());
    }

    public boolean IsLoggedIn() {
        return !prefs.GetAuthCookieValue().get().isEmpty();
    }


}
