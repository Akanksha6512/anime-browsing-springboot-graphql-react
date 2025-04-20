package com.product.productcatalogservice.service;

import com.product.productcatalogservice.model.Anime;
import com.product.productcatalogservice.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    public Page<Map<String, Object>> getAnime(Pageable pageable) {
        Page<Anime> animePage = animeRepository.findAll(pageable);
        return animePage.map(anime -> Map.of(
                "uid", anime.getUid(),
                "title", anime.getTitle(),
                "synopsis", anime.getSynopsis(),
                "img_url", anime.getImg_url()
        ));
    }
}
