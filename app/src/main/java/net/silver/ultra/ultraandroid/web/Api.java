package net.silver.ultra.ultraandroid.web;

import net.silver.ultra.ultraandroid.web.requests.LoginRequest;
import net.silver.ultra.ultraandroid.web.responses.LoginResponse;
import net.silver.ultra.ultraandroid.web.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


public final class Api {
    public static final String API_URL = "http://localhost:4665/api";
    private Retrofit retrofit;

    public UserService userService;

    public boolean IsAuthenticated(){
        throw new UnsupportedOperationException();
    }

    public <TResponse> void call(final Call<TResponse> method, final myAwesomeCallback<TResponse> callback) {
        method.enqueue(new Callback<TResponse>() {
            @Override
            public void onResponse(Response<TResponse> response) {
                if (response.isSuccess()) {
                    callback.onSuccess(response.body());
                }
                if (response.code() == 430) {
                    callback.onError(new ApiError());
                }
                if (response.code() == 401) {
                    try {
                        LoginRequest loginRequest = giveMeUserRequest.call();
                        userService.login(loginRequest)
                                .enqueue(new Callback<LoginResponse>() {
                                    @Override
                                    public void onResponse(Response<LoginResponse> response) {
                                        if (response.isSuccess()) {
                                            call(method, callback);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        callback.onError(new ApiError());
                                    }
                                });
                    } catch (Exception e) {

                    }
                }
                //403
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(new ApiError());
            }
        });
    }

    Callable<LoginRequest> giveMeUserRequest;
    public void setGiveMeUserRequest(Callable<LoginRequest>  e){
        giveMeUserRequest = e;
    }

    public Api() {
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url);
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }
}
