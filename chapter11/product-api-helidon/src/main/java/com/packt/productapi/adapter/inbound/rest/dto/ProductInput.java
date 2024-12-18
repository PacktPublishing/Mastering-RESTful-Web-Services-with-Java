package com.packt.productapi.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductInput(
        @NotBlank
        @Size(min = 3, max = 255)
        String name,
        @NotBlank
        @Size(min = 10, max = 255)
        String description,
        @NotNull
        @Positive
        BigDecimal price) {}

