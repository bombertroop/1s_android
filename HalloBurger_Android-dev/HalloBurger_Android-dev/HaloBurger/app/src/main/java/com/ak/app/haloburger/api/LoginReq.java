package com.ak.app.haloburger.api;

import com.google.gson.Gson;

/**
 * Created by el on 04/06/18.
 */

public class LoginReq {

    private String login;
    private String password;

    public String toJson() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public LoginReq(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
