package com.product.productreviewservice.repository;

import com.product.productreviewservice.model.Review;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CassandraRepository<Review, String> {

    List<Review> findByProductId(String productId);
}
