
package com.ak.app.haloburger.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationList {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("restaurants")
    @Expose
    private List<Restaurant> restaurants = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
