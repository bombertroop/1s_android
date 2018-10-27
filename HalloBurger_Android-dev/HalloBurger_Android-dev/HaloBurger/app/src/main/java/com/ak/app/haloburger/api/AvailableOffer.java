
package com.ak.app.haloburger.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailableOffer {

    @SerializedName("bonus_points")
    @Expose
    private Object bonusPoints;
    @SerializedName("bonus_points_ftu")
    @Expose
    private Object bonusPointsFtu;
    @SerializedName("chain_id")
    @Expose
    private Integer chainId;
    @SerializedName("constant_value")
    @Expose
    private Object constantValue;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("daysOfWeek")
    @Expose
    private Object daysOfWeek;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("enable_ncr_survey")
    @Expose
    private Boolean enableNcrSurvey;
    @SerializedName("expiryDate")
    @Expose
    private String expiryDate;
    @SerializedName("fineprint")
    @Expose
    private String fineprint;
    @SerializedName("having_rule")
    @Expose
    private Boolean havingRule;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isActive")
    @Expose
    private Object isActive;
    @SerializedName("is_multiplier")
    @Expose
    private Boolean isMultiplier;
    @SerializedName("is_online_order")
    @Expose
    private Boolean isOnlineOrder;
    @SerializedName("max_amount")
    @Expose
    private Integer maxAmount;
    @SerializedName("min_amount")
    @Expose
    private Integer minAmount;
    @SerializedName("min_subtotal_criteria_for_receipt_approval")
    @Expose
    private Integer minSubtotalCriteriaForReceiptApproval;
    @SerializedName("multiplier")
    @Expose
    private String multiplier;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ncr_assign_loyalty_offer")
    @Expose
    private Boolean ncrAssignLoyaltyOffer;
    @SerializedName("smg_survey_id")
    @Expose
    private Object smgSurveyId;
    @SerializedName("survey_id")
    @Expose
    private Integer surveyId;
    @SerializedName("timeEnd")
    @Expose
    private String timeEnd;
    @SerializedName("timeStart")
    @Expose
    private String timeStart;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("web_based_survey_url")
    @Expose
    private Object webBasedSurveyUrl;

    public Object getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Object bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Object getBonusPointsFtu() {
        return bonusPointsFtu;
    }

    public void setBonusPointsFtu(Object bonusPointsFtu) {
        this.bonusPointsFtu = bonusPointsFtu;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public Object getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(Object constantValue) {
        this.constantValue = constantValue;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Object daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Boolean getEnableNcrSurvey() {
        return enableNcrSurvey;
    }

    public void setEnableNcrSurvey(Boolean enableNcrSurvey) {
        this.enableNcrSurvey = enableNcrSurvey;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFineprint() {
        return fineprint;
    }

    public void setFineprint(String fineprint) {
        this.fineprint = fineprint;
    }

    public Boolean getHavingRule() {
        return havingRule;
    }

    public void setHavingRule(Boolean havingRule) {
        this.havingRule = havingRule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getIsActive() {
        return isActive;
    }

    public void setIsActive(Object isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsMultiplier() {
        return isMultiplier;
    }

    public void setIsMultiplier(Boolean isMultiplier) {
        this.isMultiplier = isMultiplier;
    }

    public Boolean getIsOnlineOrder() {
        return isOnlineOrder;
    }

    public void setIsOnlineOrder(Boolean isOnlineOrder) {
        this.isOnlineOrder = isOnlineOrder;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMinSubtotalCriteriaForReceiptApproval() {
        return minSubtotalCriteriaForReceiptApproval;
    }

    public void setMinSubtotalCriteriaForReceiptApproval(Integer minSubtotalCriteriaForReceiptApproval) {
        this.minSubtotalCriteriaForReceiptApproval = minSubtotalCriteriaForReceiptApproval;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNcrAssignLoyaltyOffer() {
        return ncrAssignLoyaltyOffer;
    }

    public void setNcrAssignLoyaltyOffer(Boolean ncrAssignLoyaltyOffer) {
        this.ncrAssignLoyaltyOffer = ncrAssignLoyaltyOffer;
    }

    public Object getSmgSurveyId() {
        return smgSurveyId;
    }

    public void setSmgSurveyId(Object smgSurveyId) {
        this.smgSurveyId = smgSurveyId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getWebBasedSurveyUrl() {
        return webBasedSurveyUrl;
    }

    public void setWebBasedSurveyUrl(Object webBasedSurveyUrl) {
        this.webBasedSurveyUrl = webBasedSurveyUrl;
    }

}
