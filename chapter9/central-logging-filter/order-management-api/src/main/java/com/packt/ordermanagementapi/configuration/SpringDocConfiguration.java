package com.packt.ordermanagementapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    OpenAPI apiInfo(@Value("${application.version}") String version) {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Order Management API")
                                .description("API for managing orders")
                                .version(version)
                );
    }
}