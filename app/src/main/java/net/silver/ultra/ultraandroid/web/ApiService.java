package net.silver.ultra.ultraandroid.web;

import net.silver.ultra.ultraandroid.web.services.UserService;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by folbo on 2016-01-12.
 */
public class ApiService {
    public static final String API_URL = "https://127.0.0.1:4455/api/";
    private Retrofit retrofit;

    private UserService userService;

    public ApiService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

}
