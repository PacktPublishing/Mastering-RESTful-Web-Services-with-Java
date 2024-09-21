package com.packt.ordermanagementapi.adapter.outbound.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsApiConfiguration {

    @Bean
    public ProductsApi getProductsApi() {
        return new ProductsApi();
    }
}
