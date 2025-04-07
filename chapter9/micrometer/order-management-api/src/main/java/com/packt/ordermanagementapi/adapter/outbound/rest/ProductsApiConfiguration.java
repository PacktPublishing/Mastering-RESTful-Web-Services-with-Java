package com.packt.ordermanagementapi.adapter.outbound.rest;

import brave.handler.MutableSpan;
import brave.handler.SpanHandler;
import brave.propagation.TraceContext;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
                .build();
    }

    private ClientHttpRequestFactory customClientHttpRequestFactory() {
        Duration duration = Duration.ofSeconds(6);
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(duration)
                .withReadTimeout(duration);

        return ClientHttpRequestFactories.get(settings);
    }

    @Bean
    public SpanHandler logSpanHandler(){
        return new LogSpanHandler();
    }

    static final class LogSpanHandler extends SpanHandler {
        final Logger logger = Logger.getLogger(ProductsApiConfiguration.class.getName());

        LogSpanHandler() {
        }

        public boolean end(TraceContext context, MutableSpan span, SpanHandler.Cause cause) {
            if (!this.logger.isLoggable(Level.INFO)) {
                return false;
            } else {
                this.logger.info(span.toString());
                return true;
            }
        }

        public String toString() {
            return "LogSpanHandler{name=" + this.logger.getName() + "}";
        }
    }
}
