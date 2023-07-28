package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.ProductReview;
import com.mercury.SpringBootRestDemo.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ProductReviewService reviewService;

    @Autowired
    public ReviewController(ProductReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ProductReview review) {
        try {
            ProductReview addedReview = reviewService.addReview(review);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product review added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product review.");
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductReview>> getReviewsByProductId(@PathVariable int productId) {
        try {
            List<ProductReview> reviews = reviewService.getReviewsByProductId(productId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
