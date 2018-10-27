package com.ak.app.haloburger.api;

import com.google.gson.Gson;

/**
 * Created by el on 04/06/18.
 */

public class PromoCodeReq extends RelevantApi{
    private String auth_token;
    private String code;
    private String force;

    public PromoCodeReq(){}

    public String toJson() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
}
