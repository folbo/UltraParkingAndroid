package net.silver.ultra.ultraandroid.web;

/**
 * Created by folbo on 2016-01-12.
 */
public interface myAwesomeCallback<T>{
    void onSuccess(T response);

    void onError(ApiError apiError);
}

