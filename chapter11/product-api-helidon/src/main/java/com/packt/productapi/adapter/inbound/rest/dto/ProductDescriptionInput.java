package com.packt.productapi.adapter.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductDescriptionInput(@NotBlank
                                      @Size(min = 10, max = 255)
                                      String description) {}
