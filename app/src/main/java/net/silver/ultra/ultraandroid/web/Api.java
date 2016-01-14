package net.silver.ultra.ultraandroid.web;

import net.silver.ultra.ultraandroid.web.services.UserService;

import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by folbo on 2016-01-12.
 */
public class Api {
    public static final String API_URL = "http://10.0.3.2:4665/api/";
    private Retrofit retrofit;

    public UserService userService;

    public Api() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }
}
