package com.packt.productapi.adapter.inbound.rest;


import com.packt.productapi.adapter.exception.UnexpectedServerError;
import com.packt.productapi.adapter.inbound.rest.configuration.ValidSku;
import com.packt.productapi.adapter.inbound.rest.dto.PaginatedProducts;
import com.packt.productapi.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductInput;
import com.packt.productapi.adapter.inbound.rest.dto.ProductOutput;
import com.packt.productapi.adapter.inbound.rest.mapper.ProductMapper;
import com.packt.productapi.usecase.ProductsCommandUseCase;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductsApiController implements ProductsApi {

    private final ProductsQueryUseCase productsQueryUseCase;
    private final ProductsCommandUseCase productsCommandUseCase;
    private final ProductMapper productMapper;

    private static final List<String> ALLOWED_CONTENT_TYPES = List.of("image/png", "image/jpeg");

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
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") @ValidSku String productId) {
        productsCommandUseCase.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{productId}")
    @Override
    public ResponseEntity<ProductOutput> editProduct(@PathVariable("productId") @ValidSku String productId,
                                                     @RequestBody @Valid ProductDescriptionInput input) {
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<List<ProductOutput>> getProducts() {
        final var products = productsQueryUseCase.getAllProducts(PageRequest.ofSize(Integer.MAX_VALUE))
                .stream()
                .map(productMapper::toProductOutput)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(products);
    }

    @GetMapping(produces = "application/vnd.packt-v2+json")
    @Override
    public ResponseEntity<PaginatedProducts> getProductsV2(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        final var products = productsQueryUseCase.getAllProducts(PageRequest.of(page, limit));
        int totalPages = products.getTotalPages();
        List<ProductOutput> output = products.stream()
                .map(productMapper::toProductOutput)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginatedProducts(totalPages, output));
    }

    @PutMapping(value = "/{productId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Override
    public ResponseEntity<String> uploadProductPhoto(@PathVariable("productId") @ValidSku String productId,
                                                     MultipartFile file) {
        if (file == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");
        }
        String contentType = file.getContentType();
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    "Unsupported content type: " + contentType);
        }
        try {
            var photoHash = productsCommandUseCase.createOrUpdateProductPhoto(productId, file.getBytes(), file.getContentType());
            return ResponseEntity.ok(photoHash);
        } catch (IOException e) {
            throw new UnexpectedServerError("Error to access the photo file.", e);
        }
    }

    @GetMapping(value = "/{productId}/photo")
    @Override
    public ResponseEntity<byte[]> downloadProductPhoto(@PathVariable("productId") @ValidSku String productId) {
        var photo = productsQueryUseCase.getProductPhotoById(productId);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(photo.getPhotoContentType()))
//                .cacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
                .eTag(getHashString(photo.getPhoto()))
                .body(photo.getPhoto());
    }

    private String getHashString(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return Base64.getEncoder().encodeToString(md.digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
