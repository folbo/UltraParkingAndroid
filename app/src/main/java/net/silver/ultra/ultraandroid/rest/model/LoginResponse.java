package net.silver.ultra.ultraandroid.rest.model;

import java.io.Serializable;

/**
 * Created by Sylwekqaz on 15.01.2016.
 */
public class LoginResponse implements Serializable {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

