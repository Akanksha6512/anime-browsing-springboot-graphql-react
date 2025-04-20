package com.product.graphql_gateway_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${PRODUCT_SERVICE_URL}")
    private String productServiceBaseUrl;

    @Value("${REVIEW_SERVICE_URL}")
    private String reviewServiceBaseUrl;

    @Bean
    public WebClient productServiceWebClient(WebClient.Builder builder) {
        return builder.baseUrl(productServiceBaseUrl).build();
    }

    @Bean
    public WebClient reviewServiceWebClient(WebClient.Builder builder) {
        return builder.baseUrl(reviewServiceBaseUrl).build();
    }

}
