package com.ak.app.haloburger.api;

import com.google.gson.Gson;

/**
 * Created by el on 04/06/18.
 */

public class ForgotPasswordReq extends RelevantApi {
    private String email;
    private String register_type;

    public String getRegister_type() {
        return register_type;
    }

    public void setRegister_type(String register_type) {
        this.register_type = register_type;
    }

    public ForgotPasswordReq(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toJson() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }
}
