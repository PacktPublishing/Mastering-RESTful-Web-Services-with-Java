package com.packt.ordermanagementapi.configuration;

import com.packt.ordermanagementapi.adapter.outbound.ApiClient;
import com.packt.ordermanagementapi.adapter.outbound.rest.ProductsApi;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductsApiBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ProductsApi) {
            ProductsApi productsApi = (ProductsApi) bean;
            ApiClient apiClient = productsApi.getApiClient();
            String correlationId = MDC.get("correlationId");
            if (correlationId != null) {
                apiClient.addDefaultHeader("X-Correlation-Id", correlationId);
            }
            productsApi.setApiClient(apiClient);
        }
        return bean;
    }
}
