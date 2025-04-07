package com.packt.productapi.adapter.inbound.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductDescriptionInput(@NotBlank
                                      @Size(min = 10, max = 255)
                                      @Schema(name = "description", requiredMode = Schema.RequiredMode.REQUIRED, example = "Ergonomic Keyboard 2.0")
                                      String description) {
}
