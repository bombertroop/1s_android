
package com.ak.app.haloburger.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RewardActivityList {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("activities")
    @Expose
    private List<Activity> activities = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
