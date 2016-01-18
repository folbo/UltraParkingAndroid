package net.silver.ultra.ultraandroid.Authentication.event;

import net.silver.ultra.ultraandroid.parking.model.ReserveReturns;

/**
 * Created by Sylwekqaz on 16.01.2016.
 */
public class UserLoggedIn {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public UserLoggedIn(String userId) {

        this.userId = userId;
    }
}

