package com.packt.productapi.adapter.inbound.rest.dto;

import java.math.BigDecimal;

public record ProductOutput(String name, String sku, String description, BigDecimal price) {

}

