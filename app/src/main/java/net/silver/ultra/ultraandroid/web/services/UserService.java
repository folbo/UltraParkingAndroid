package net.silver.ultra.ultraandroid.web.services;

import net.silver.ultra.ultraandroid.entities.User;
import net.silver.ultra.ultraandroid.web.requests.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/account/login")
    Call<User> login(@Body LoginRequest body);

    //@POST("/account/logoff")
    //Call<User> logoff(@Body LogoutRequest body);
}
