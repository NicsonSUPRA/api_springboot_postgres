package com.nicson.apipostgres.models.enums;

public enum OrderStatus {
    WAITING_PAYMENT("waiting payment"),
    PAID("paid"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    final String value;

    private OrderStatus(String status) {
        this.value = status;
    }

}
