package net.silver.ultra.ultraandroid.web.requests;

/**
 * Created by folbo on 2016-01-12.
 */

import net.silver.ultra.ultraandroid.entities.User;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class LoginRequest {
    final String email;
    final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;

    }
}

