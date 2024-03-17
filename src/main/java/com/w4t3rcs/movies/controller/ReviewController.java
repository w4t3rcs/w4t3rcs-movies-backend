package com.w4t3rcs.movies.controller;

import com.w4t3rcs.movies.dto.document.ReviewDto;
import com.w4t3rcs.movies.dto.request.ReviewRequest;
import com.w4t3rcs.movies.service.data.ReviewService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1.0/reviews")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getAllReviews(@PathVariable ObjectId id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PostMapping
    public ResponseEntity<ReviewDto> postReview(@RequestBody ReviewRequest request) {
        return new ResponseEntity<>(reviewService.createReview(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable ObjectId id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }
}
