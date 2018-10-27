
package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedeemWarnResponse {

    @SerializedName("warn_tile")
    @Expose
    private String warnTile;
    @SerializedName("warn_body")
    @Expose
    private String warnBody;

    public String getWarnTile() {
        return warnTile;
    }

    public void setWarnTile(String warnTile) {
        this.warnTile = warnTile;
    }

    public String getWarnBody() {
        return warnBody;
    }

    public void setWarnBody(String warnBody) {
        this.warnBody = warnBody;
    }

}
