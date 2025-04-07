package com.packt.productapi.adapter.inbound.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PaginatedProducts(@Schema(name = "totalPages", example = "10")
                                Integer totalPages, List<ProductOutput> products) {

}

