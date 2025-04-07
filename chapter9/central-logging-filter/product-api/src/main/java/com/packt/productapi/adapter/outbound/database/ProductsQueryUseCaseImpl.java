package com.packt.productapi.adapter.outbound.database;

import com.packt.productapi.adapter.exception.EntityNotFoundException;
import com.packt.productapi.domain.Product;
import com.packt.productapi.domain.ProductPhoto;
import com.packt.productapi.usecase.ProductsQueryUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

    private final ProductsRepository productsRepository;
    private final PhotoProductsRepository photoProductsRepository;

    public ProductsQueryUseCaseImpl(ProductsRepository productsRepository,
                                    PhotoProductsRepository photoProductsRepository) {
        this.productsRepository = productsRepository;
        this.photoProductsRepository = photoProductsRepository;
    }

    @Override
    public Product getProductById(String productId) {
        return productsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public ProductPhoto getProductPhotoById(String productId) {
        return photoProductsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public Page<? extends Product> getAllProducts(Pageable pageRequest) {
        return productsRepository.findAll(pageRequest);
    }
}
