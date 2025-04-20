package com.product.productcatalogservice.repository;

import com.product.productcatalogservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
