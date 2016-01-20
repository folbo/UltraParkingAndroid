package net.silver.ultra.ultraandroid.Authentication.event;

/**
 * Created by Sylwekqaz on 16.01.2016.
 */
public class UserLoggedIn {
    private String userId;
    private String userEmail;

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserLoggedIn(String userId, String userEmail) {

        this.userId = userId;
        this.userEmail = userEmail;
    }
}

