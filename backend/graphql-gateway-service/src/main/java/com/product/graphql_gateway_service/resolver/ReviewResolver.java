package com.product.graphql_gateway_service.resolver;

import com.product.graphql_gateway_service.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Controller
public class ReviewResolver {
//    private final WebClient webClient;
//    private final String reviewServiceUrl;
//
//    public ReviewResolver(WebClient.Builder webClientBuilder, @Value("${review-service.url}") String reviewServiceUrl) {
//        this.webClient = webClientBuilder.build();
//        this.reviewServiceUrl = reviewServiceUrl;
//    }
    @Autowired
    private WebClient reviewServiceWebClient;

    @QueryMapping
    public Flux<Review> reviews(@Argument String product_id) {
        return reviewServiceWebClient.get()
                .uri("/products/" + product_id) // Construct URL with path variable
                .retrieve()
                .bodyToFlux(Review.class);
    }
}
