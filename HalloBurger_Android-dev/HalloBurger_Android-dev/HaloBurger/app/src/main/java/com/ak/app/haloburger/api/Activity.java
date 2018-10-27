
package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity {

    @SerializedName("reward")
    @Expose
    private RewardActivity reward;
    @SerializedName("claim_date")
    @Expose
    private String claimDate;

    public RewardActivity getReward() {
        return reward;
    }

    public void setReward(RewardActivity reward) {
        this.reward = reward;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

}
