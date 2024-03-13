package com.w4t3rcs.movies.controller;

import com.w4t3rcs.movies.data.dao.ReviewRepository;
import com.w4t3rcs.movies.data.document.Movie;
import com.w4t3rcs.movies.data.document.Review;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/v1.0/reviews")
@RestController
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getAllReviews(@PathVariable ObjectId id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        return reviewOptional.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Review> postReview(@RequestBody Map<String, String> payload) {
        String reviewBody = payload.get("reviewBody");
        Review review = reviewRepository.save(new Review(null, reviewBody));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(payload.get("imdbId")))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
}
