package net.silver.ultra.ultraandroid.util;

import net.silver.ultra.ultraandroid.Authentication.rest.AuthenticationRest;
import net.silver.ultra.ultraandroid.parking.rest.ParkingRestService;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Sylwekqaz on 17.01.2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class RestManager {

    @RestService
    AuthenticationRest authenticationRest;

    @RestService
    ParkingRestService parkingRestService;

    @AfterInject
    void init(){

    }

    public AuthenticationRest getAuthenticationRest() {
        return authenticationRest;
    }

    public ParkingRestService getParkingRestService() { return parkingRestService; }
}
