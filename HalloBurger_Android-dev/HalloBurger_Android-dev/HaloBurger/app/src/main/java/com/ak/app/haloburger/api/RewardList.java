
package com.ak.app.haloburger.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RewardList {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("balance")
    @Expose
    private Balance balance;
    @SerializedName("rewards_image")
    @Expose
    private String rewardsImage;
    @SerializedName("rewards")
    @Expose
    private List<Reward> rewards = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public String getRewardsImage() {
        return rewardsImage;
    }

    public void setRewardsImage(String rewardsImage) {
        this.rewardsImage = rewardsImage;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

}
