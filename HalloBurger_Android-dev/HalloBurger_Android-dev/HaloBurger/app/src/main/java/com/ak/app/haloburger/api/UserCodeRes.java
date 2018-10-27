package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by el on 05/06/18.
 */

public class UserCodeRes {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("usercode")
    @Expose
    private String usercode;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
}
