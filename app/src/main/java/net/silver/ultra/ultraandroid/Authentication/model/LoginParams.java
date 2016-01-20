package net.silver.ultra.ultraandroid.Authentication.model;

import java.io.Serializable;

/**
 * Created by Sylwekqaz on 15.01.2016.
 */
public class LoginParams implements Serializable {
    private String email;
    private String password;

    public LoginParams() {
    }

    public LoginParams(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
