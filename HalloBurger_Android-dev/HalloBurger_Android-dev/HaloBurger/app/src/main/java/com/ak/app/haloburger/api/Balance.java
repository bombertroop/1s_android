
package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Balance {

    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("milestone_points")
    @Expose
    private Integer milestonePoints;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMilestonePoints() {
        return milestonePoints;
    }

    public void setMilestonePoints(Integer milestonePoints) {
        this.milestonePoints = milestonePoints;
    }

}
