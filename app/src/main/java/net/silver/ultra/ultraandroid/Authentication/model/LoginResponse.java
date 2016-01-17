package net.silver.ultra.ultraandroid.Authentication.model;

import java.io.Serializable;

/**
 * Created by Sylwekqaz on 15.01.2016.
 */
public class LoginResponse implements Serializable {
    private String userId;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

