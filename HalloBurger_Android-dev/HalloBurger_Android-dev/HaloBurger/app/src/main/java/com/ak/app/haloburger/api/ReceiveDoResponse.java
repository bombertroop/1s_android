package com.ak.app.haloburger.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiveDoResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("delivery_order")
    @Expose
    DeliveryOrderResponse deliveryOrder;

    public DeliveryOrderResponse getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrderResponse deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
