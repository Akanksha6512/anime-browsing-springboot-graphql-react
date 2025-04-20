package com.product.productcatalogservice.controller;

import com.product.productcatalogservice.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAnimes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Map<String, Object>> animePage = animeService.getAnime(pageable);

        Map<String, Object> response = Map.of(
                "items", animePage.getContent(),
                "totalCount", (int) animePage.getTotalElements(),
                "page", animePage.getNumber() + 1, // Page number is 0-based
                "pageSize", animePage.getSize(),
                "totalPages", animePage.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }
}
