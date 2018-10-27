
package com.ak.app.haloburger.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reward {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("fineprint")
    @Expose
    private String fineprint;
    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("expiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("chain_id")
    @Expose
    private Integer chainId;
    @SerializedName("survey_id")
    @Expose
    private Object surveyId;
    @SerializedName("reward_type")
    @Expose
    private Integer rewardType;
    @SerializedName("POSCode")
    @Expose
    private String pOSCode;
    @SerializedName("expired")
    @Expose
    private Boolean expired;
    @SerializedName("sort_by_id")
    @Expose
    private Integer sortById;
    @SerializedName("gifter")
    @Expose
    private Object gifter;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("image_thumbnail_url")
    @Expose
    private String imageThumbnailUrl;
    @SerializedName("new_reward")
    @Expose
    private Boolean newReward;
    @SerializedName("additional_fields")
    @Expose
    private List<Object> additionalFields = null;
    @SerializedName("device_reward_expired")
    @Expose
    private Boolean deviceRewardExpired;

    public String getExpirestate() {
        return expirestate;
    }

    public void setExpirestate(String expirestate) {
        this.expirestate = expirestate;
    }

    private String expirestate = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getFineprint() {
        return fineprint;
    }

    public void setFineprint(String fineprint) {
        this.fineprint = fineprint;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public Object getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Object surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public String getPOSCode() {
        return pOSCode;
    }

    public void setPOSCode(String pOSCode) {
        this.pOSCode = pOSCode;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Integer getSortById() {
        return sortById;
    }

    public void setSortById(Integer sortById) {
        this.sortById = sortById;
    }

    public Object getGifter() {
        return gifter;
    }

    public void setGifter(Object gifter) {
        this.gifter = gifter;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(String imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

    public Boolean getNewReward() {
        return newReward;
    }

    public void setNewReward(Boolean newReward) {
        this.newReward = newReward;
    }

    public List<Object> getAdditionalFields() {
        return additionalFields;
    }

    public void setAdditionalFields(List<Object> additionalFields) {
        this.additionalFields = additionalFields;
    }

    public Boolean getDeviceRewardExpired() {
        return deviceRewardExpired;
    }

    public void setDeviceRewardExpired(Boolean deviceRewardExpired) {
        this.deviceRewardExpired = deviceRewardExpired;
    }

}
