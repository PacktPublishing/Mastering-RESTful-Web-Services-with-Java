package com.packt.productapi.adapter.inbound.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.packt.productapi.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public record ProductInput(
        @NotBlank
        @Size(min = 3, max = 255)
        @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Keyboard")
        @JsonProperty("name")
        String name,
        @NotBlank
        @Size(min = 10, max = 255)
        @Schema(name = "description", requiredMode = Schema.RequiredMode.REQUIRED, example = "Ergonomic Keyboard")
        @JsonProperty("description")
        String description,
        @NotNull
        @Positive
        @Schema(name = "price", requiredMode = Schema.RequiredMode.REQUIRED, example = "60.0")
        @JsonProperty("price")
        BigDecimal price) {

    public Product toProduct(String productId) {
        return new Product(name(),
                productId,
                description(),
                price());
    }
}

