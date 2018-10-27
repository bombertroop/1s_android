package com.ak.app.haloburger.api;

import com.google.gson.Gson;

/**
 * Created by el on 04/06/18.
 */

public class ChangePasswordReq extends RelevantApi{
    private String current_password;
    private String password;
    private String password_confirmation;
    private String auth_token;

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public ChangePasswordReq(){}

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String toJson() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

}
