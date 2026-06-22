package com.ERPs.MeuERP.entities;

import java.io.Serializable;
import java.time.Instant;

public class OrderItem implements Serializable {

    private Instant moment;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Instant moment, Double price) {
        this.moment = moment;
        this.price = price;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
