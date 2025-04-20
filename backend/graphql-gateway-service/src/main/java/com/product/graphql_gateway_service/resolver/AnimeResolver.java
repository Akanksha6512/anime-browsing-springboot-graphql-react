package com.product.graphql_gateway_service.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
public class AnimeResolver {

    @Autowired
    private WebClient productServiceWebClient;

    @QueryMapping
    public Mono<Map<String, Object>> anime(
            @Argument(name = "page") int page,
            @Argument(name = "pageSize") int pageSize) {

        return productServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/anime")
                        .queryParam("page", page - 1) // Spring Data page is 0-based
                        .queryParam("size", pageSize)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
                    int totalCount = (Integer) response.get("totalCount");
                    int totalPages = (Integer) response.get("totalPages");
                    return Map.of(
                            "items", items,
                            "totalCount", totalCount,
                            "page", page,
                            "pageSize", pageSize,
                            "totalPages", totalPages
                    );
                });
    }
}
