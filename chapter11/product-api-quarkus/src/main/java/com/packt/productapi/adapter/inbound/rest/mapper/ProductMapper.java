package com.packt.productapi.adapter.inbound.rest.mapper;

import com.packt.productapi.adapter.inbound.rest.dto.ProductInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import com.packt.productapi.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    @Mapping(source = "productId", target = "sku")
    Product toProduct(ProductInput productInput, String productId);

    ProductOutput toProductOutput(Product product);
}
