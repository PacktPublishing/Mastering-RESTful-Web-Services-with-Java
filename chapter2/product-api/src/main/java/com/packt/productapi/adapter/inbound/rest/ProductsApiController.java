package com.packt.productapi.adapter.inbound.rest;


import com.packt.productapi.adapter.inbound.rest.configuration.ValidSku;
import com.packt.productapi.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import com.packt.productapi.adapter.inbound.rest.mapper.ProductMapper;
import com.packt.productapi.usecase.ProductsCommandUseCase;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductsApiController implements ProductsApi {

    private final ProductsQueryUseCase productsQueryUseCase;
    private final ProductsCommandUseCase productsCommandUseCase;
    private final ProductMapper productMapper;

    public ProductsApiController(ProductsQueryUseCase productsQueryUseCase,
                                 ProductsCommandUseCase productsCommandUseCase,
                                 ProductMapper productMapper) {
        this.productsQueryUseCase = productsQueryUseCase;
        this.productsCommandUseCase = productsCommandUseCase;
        this.productMapper = productMapper;
    }

    @PutMapping(value = "/{productId}")
    @Override
    public ResponseEntity<ProductOutput> createOrUpdateProduct(@PathVariable("productId") @ValidSku String productId,
                                                               @Valid @RequestBody ProductInput productInput) {
        final var product = productsCommandUseCase.createProduct(productInput.toProduct(productId));
        HttpStatus status = product.isNewProduct() ? HttpStatus.CREATED : HttpStatus.OK;
        return ResponseEntity.status(status)
                .body(productMapper.toProductOutput(product.product()));
    }

    @DeleteMapping(value = "/{productId}")
    @Override
    public ResponseEntity<Void> deleteProduct(@ValidSku @PathVariable("productId") String productId) {
        productsCommandUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{productId}")
    @Override
    public ResponseEntity<ProductOutput> editProductDescription(@PathVariable("productId") @ValidSku String productId,
                                                                @Valid @RequestBody ProductDescriptionInput input) {
        final var product = productsCommandUseCase.updateProductDescription(productId, input.description());
        return ResponseEntity.status(HttpStatus.OK)
                .body(productMapper.toProductOutput(product));
    }

    @GetMapping(value = "/{productId}")
    @Override
    public ResponseEntity<ProductOutput> getProductById(@PathVariable("productId") @ValidSku String productId) {
        final var product = productsQueryUseCase.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productMapper.toProductOutput(product));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ProductOutput>> getProducts() {
        final var products = productsQueryUseCase.getAllProducts()
                .stream()
                .map(productMapper::toProductOutput)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(products);
    }

}
