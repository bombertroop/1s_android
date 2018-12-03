package com.ak.app.haloburger.model;

public class Sales {
    private Product product;
    private EventSales eventSales;
    private int qty;
    private float net;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public EventSales getEventSales() {
        return eventSales;
    }

    public void setEventSales(EventSales eventSales) {
        this.eventSales = eventSales;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getNet() {
        return net;
    }

    public void setNet(float net) {
        this.net = net;
    }
}
