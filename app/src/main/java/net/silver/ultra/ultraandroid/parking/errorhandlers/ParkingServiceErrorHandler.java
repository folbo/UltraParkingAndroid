package net.silver.ultra.ultraandroid.parking.errorhandlers;

import android.os.Handler;
import android.widget.Toast;

import net.silver.ultra.ultraandroid.MyApp;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Created by folbo on 2016-01-17.
 */

@EBean
public class ParkingServiceErrorHandler implements RestErrorHandler {
    @App
    MyApp myApp;

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException exception = (HttpClientErrorException) e;
            HttpStatus statusCode = exception.getStatusCode();

            switch(statusCode.value()){
                case 422:
                    showToast("Brak wolnych miejsc");
                    break;
                case 401:
                    showToast("Musisz się zalogować, aby skorzystać z tej funkcji");
                    break;
            }
        }
        else if(e instanceof HttpServerErrorException)
        {
            HttpServerErrorException exception = (HttpServerErrorException) e;
            HttpStatus statusCode = exception.getStatusCode();

            switch(statusCode.value()){
                case 500:
                    showToast("Już masz zarezerwowane miejsce. ");
                    break;
            }
        }
    }

    protected void showToast(final String message){
        Handler mainThreadHandler = new Handler(myApp.getMainLooper());
        Runnable showMessage = new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(myApp, message, Toast.LENGTH_SHORT);
                toast.show();
            }
        };
        mainThreadHandler.post(showMessage);
    }
}