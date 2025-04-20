package com.product.graphql_gateway_service.resolver;

import com.product.graphql_gateway_service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Controller
public class ProductResolver {
//    private final WebClient webClient;
//    private final String productServiceUrl;
//
//    public ProductResolver(WebClient.Builder webClientBuilder, @Value("${product-catalog-service.url}") String productServiceUrl) {
//        this.webClient = webClientBuilder.build();
//        this.productServiceUrl = productServiceUrl;
//    }
    @Autowired
    private WebClient productServiceWebClient;

    @QueryMapping
    public Flux<Product> products() {
        return productServiceWebClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }
}
