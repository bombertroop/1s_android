package com.ak.app.haloburger.api;

import com.google.gson.Gson;

/**
 * Created by el on 05/06/18.
 */

public class RewardClaimWarnReq extends AuthReq {
    private String reward_id;
    private String lat;
    private String lng;
    private String location;
    private String warn;

    public RewardClaimWarnReq(){

    }

    public String toJson() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getReward_id() {
        return reward_id;
    }

    public void setReward_id(String reward_id) {
        this.reward_id = reward_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }
}
