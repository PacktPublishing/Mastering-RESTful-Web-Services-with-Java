package com.packt.ordermanagementapi.domain;

public enum StatusEnum {
    PENDING("Pending"),

    APPROVED("Approved"),

    CANCELLED("Cancelled");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }


}
