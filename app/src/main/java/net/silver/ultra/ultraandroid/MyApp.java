package net.silver.ultra.ultraandroid;

import android.app.Application;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import net.silver.ultra.ultraandroid.Authentication.event.UserLoggedIn;
import net.silver.ultra.ultraandroid.Authentication.event.UserLoggedOut;
import net.silver.ultra.ultraandroid.Authentication.rest.AuthenticationRest;
import net.silver.ultra.ultraandroid.parking.event.ParkingReservedEvent;
import net.silver.ultra.ultraandroid.util.OttoBus;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by Sylwekqaz on 16.01.2016.
 */
@EApplication
public class MyApp extends Application {

    @Pref
    public AppPrefs_ prefs;

    @Bean
    OttoBus bus;

    @RestService
    AuthenticationRest authenticationRest;

    @AfterInject
    void init(){
        bus.register(this);
    }

    public OttoBus getBus() {
        return bus;
    }

    @Subscribe
    public void onParkingReservedEvent(ParkingReservedEvent event) {
        String message = String.format("Miejsce %d %s", event.getReservedPlace().getNumber(), event.getReservedPlace().getSegmentName());
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
