package net.silver.ultra.ultraandroid.transaction.errorhandlers;

import android.os.Handler;
import android.widget.Toast;

import net.silver.ultra.ultraandroid.MyApp;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by folbo on 2016-01-21.
 */

@EBean
public class TransactionServiceErrorHandler implements RestErrorHandler {
    @App
    MyApp myApp;

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException exception = (HttpClientErrorException) e;
            HttpStatus statusCode = exception.getStatusCode();

            switch(statusCode.value()){
                case 401:
                    showToast("Musisz się zalogować, aby skorzystać z tej funkcji");
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