
package com.ak.app.haloburger.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantLocateParent {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("restaurants")
    @Expose
    private List<RestaurantLocate> restaurants = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<RestaurantLocate> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantLocate> restaurants) {
        this.restaurants = restaurants;
    }

}
