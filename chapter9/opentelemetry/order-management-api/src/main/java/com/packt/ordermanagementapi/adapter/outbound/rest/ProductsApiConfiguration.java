package com.packt.ordermanagementapi.adapter.outbound.rest;

import com.packt.ordermanagementapi.adapter.outbound.ApiClient;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class ProductsApiConfiguration {

    @Bean
    public ProductsApi getProductsApi(ApiClient apiClient) {
        return new ProductsApi(apiClient);
    }

    @Bean
    public ApiClient getApiClient(RestClient restClient) {
        return new ApiClient(restClient);
    }

    @Bean
    public RestClient getRestClient(ObservationRegistry observationRegistry, RestClientBuilderConfigurer configurer) {
        return configurer.configure(RestClient.builder())
                .requestFactory(customClientHttpRequestFactory())
                .observationRegistry(observationRegistry)
                .defaultHeaders(httpHeaders -> {})
                .build();
    }

    private ClientHttpRequestFactory customClientHttpRequestFactory() {
        Duration duration = Duration.ofSeconds(6);
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(duration)
                .withReadTimeout(duration);

        return ClientHttpRequestFactories.get(settings);
    }
}
