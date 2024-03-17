package com.w4t3rcs.movies.service.data;

import com.w4t3rcs.movies.dao.MovieRepository;
import com.w4t3rcs.movies.dao.ReviewRepository;
import com.w4t3rcs.movies.document.Movie;
import com.w4t3rcs.movies.document.Review;
import com.w4t3rcs.movies.dto.document.ReviewDto;
import com.w4t3rcs.movies.dto.request.ReviewRequest;
import com.w4t3rcs.movies.exception.MovieNotFoundException;
import com.w4t3rcs.movies.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final MongoTemplate mongoTemplate;

    @Transactional(readOnly = true)
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewDto::fromReview)
                .toList();
    }

    @Cacheable(value = "ReviewService::getReviewById", key = "#id")
    @Transactional(readOnly = true)
    public ReviewDto getReviewById(ObjectId id) {
        final Review review = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
        return ReviewDto.fromReview(review);
    }

    @Caching(cacheable = {
            @Cacheable(value = "ReviewService::getReviewById", key = "#request.reviewDto.id != null ? #request.reviewDto.id : 'null'")
    })
    @Transactional
    public ReviewDto createReview(ReviewRequest request) {
        Review review = reviewRepository.save(request.getReviewDto().toReview());
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(request.getImdbId()))
                .apply(new Update().push("reviewIds", review))
                .first();
        return ReviewDto.fromReview(review);
    }

    @CacheEvict(value = "ReviewService::getReviewById", key = "#id")
    @Transactional
    public ReviewDto deleteReview(ObjectId id) {
        Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
        reviewRepository.delete(review);
        Movie movie = movieRepository.findByReviewId(id).orElseThrow(MovieNotFoundException::new);
        movie.getReviewIds().remove(review);
        movieRepository.save(movie);
        return ReviewDto.fromReview(review);
    }
}
