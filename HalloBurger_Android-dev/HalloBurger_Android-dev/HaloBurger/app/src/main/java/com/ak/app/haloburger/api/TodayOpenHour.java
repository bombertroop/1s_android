
package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayOpenHour {

    @SerializedName("close_at")
    @Expose
    private String closeAt;
    @SerializedName("day_of_week")
    @Expose
    private Integer dayOfWeek;
    @SerializedName("open_at")
    @Expose
    private String openAt;

    public String getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(String closeAt) {
        this.closeAt = closeAt;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOpenAt() {
        return openAt;
    }

    public void setOpenAt(String openAt) {
        this.openAt = openAt;
    }

}
