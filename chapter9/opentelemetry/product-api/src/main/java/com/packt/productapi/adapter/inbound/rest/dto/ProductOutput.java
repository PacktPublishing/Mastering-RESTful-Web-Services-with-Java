package com.packt.productapi.adapter.inbound.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductOutput(@Schema(name = "name", example = "Keyboard")
                            String name,
                            @Schema(name = "sku", description = "ID of the product", example = "AK21109")
                            String sku,
                            @Schema(name = "description", example = "Ergonomic Keyboard")
                            String description,
                            @Schema(name = "price", example = "60.0")
                            BigDecimal price) {

}

