package net.silver.ultra.ultraandroid.web.services;

import net.silver.ultra.ultraandroid.entities.User;
import net.silver.ultra.ultraandroid.web.requests.LoginRequest;
import net.silver.ultra.ultraandroid.web.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("account/login")
    Call<LoginResponse> login(@Body LoginRequest body);

    //@POST("/account/logoff")
    //Call<User> logoff(@Body LogoutRequest body);
}
