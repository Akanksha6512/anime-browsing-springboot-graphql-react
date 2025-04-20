package com.product.productcatalogservice.repository;

import com.product.productcatalogservice.model.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeRepository extends MongoRepository<Anime, String> {
    Page<Anime> findAll(Pageable pageable);
}
