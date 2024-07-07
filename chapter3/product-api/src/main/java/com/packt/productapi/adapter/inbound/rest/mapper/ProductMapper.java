package com.packt.productapi.adapter.inbound.rest.mapper;

import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import com.packt.productapi.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductOutput toProductOutput(Product product);
}
