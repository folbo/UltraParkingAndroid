package net.silver.ultra.ultraandroid.web;

import net.silver.ultra.ultraandroid.web.services.UserService;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by folbo on 2016-01-12.
 */
public class ApiService {
    public static final String API_URL = "http://localhost:4665/api/";
    private Retrofit retrofit;

    public UserService userService;

    public ApiService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

}
