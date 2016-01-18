package net.silver.ultra.ultraandroid.util;

import net.silver.ultra.ultraandroid.AppPrefs_;
import net.silver.ultra.ultraandroid.Authentication.rest.AuthenticationRest;
import net.silver.ultra.ultraandroid.MyApp;
import net.silver.ultra.ultraandroid.R;
import net.silver.ultra.ultraandroid.parking.errorhandlers.ParkingServiceErrorHandler;
import net.silver.ultra.ultraandroid.parking.rest.ParkingRestService;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Sylwekqaz on 17.01.2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RestManager {
    @StringRes(R.string.auth_cookie)
    String cookieName;

    @Pref
    AppPrefs_ prefs;
    @Bean
    ParkingServiceErrorHandler parkingServiceErrorHandler;

    @RestService
    AuthenticationRest authenticationRest;
    @RestService
    ParkingRestService parkingRestService;

    @AfterInject
    void init(){
        parkingRestService.setRestErrorHandler(parkingServiceErrorHandler);
        injectAuthCookie();
    }

    private void injectAuthCookie() {
        String cookieval = prefs.GetAuthCookieValue().getOr("");
        authenticationRest.setCookie(cookieName, cookieval);
        parkingRestService.setCookie(cookieName, cookieval);
    }

    public AuthenticationRest getAuthenticationRest() {
        return authenticationRest;
    }

    public ParkingRestService getParkingRestService() { return parkingRestService; }



    public void SaveAuthCookie() {
        String cookie = authenticationRest.getCookie(cookieName);
        prefs.edit().GetAuthCookieValue().put(cookie).apply();
        injectAuthCookie();
    }
}
