package com.packt.productapi.adapter.inbound.rest;

import com.packt.productapi.adapter.inbound.rest.configuration.ValidSku;
import com.packt.productapi.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProductsApi {

    ResponseEntity<ProductOutput> createOrUpdateProduct(@ValidSku String productId,
                                                        @Valid ProductInput productInput);

    ResponseEntity<Void> deleteProduct(@ValidSku String productId);

    ResponseEntity<ProductOutput> editProductDescription(@ValidSku String productId,
                                                         @Valid ProductDescriptionInput input);

    ResponseEntity<ProductOutput> getProductById(@ValidSku String productId);

    ResponseEntity<List<ProductOutput>> getProducts();

}
