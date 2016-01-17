package net.silver.ultra.ultraandroid;

import android.app.Application;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import net.silver.ultra.ultraandroid.parking.event.ParkingReservedEvent;
import net.silver.ultra.ultraandroid.rest.AuthenticationRest;
import net.silver.ultra.ultraandroid.rest.ErrorHandler;
import net.silver.ultra.ultraandroid.util.OttoBus;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestErrorHandler;

/**
 * Created by Sylwekqaz on 16.01.2016.
 */
@EApplication
public class MyApp extends Application {

    @Bean
    OttoBus bus;

    @RestService
    AuthenticationRest authenticationRest;

    @Bean
    ErrorHandler restErrorHandler;

    @AfterInject
    void init(){
        bus.register(this);

        authenticationRest.setRestErrorHandler(restErrorHandler);
    }

    public OttoBus getBus() {
        return bus;
    }

    @Subscribe
    public void onParkingReservedEvent(ParkingReservedEvent event) {
        Toast.makeText(this,getString(R.string.app_name),Toast.LENGTH_SHORT);
    }
}
