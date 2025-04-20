package com.product.productreviewservice.controller;

import com.product.productreviewservice.model.Review;
import com.product.productreviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @GetMapping("/products/{productId}") // New endpoint to get reviews by productId
    public List<Review> getReviewsByProductId(@PathVariable String productId) {
        return reviewRepository.findByProductId(productId); // Assuming you have this method in your repository
    }
}
