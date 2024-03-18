package com.w4t3rcs.movies.service.data;

import com.w4t3rcs.movies.dao.MovieRepository;
import com.w4t3rcs.movies.dao.ReviewRepository;
import com.w4t3rcs.movies.document.Movie;
import com.w4t3rcs.movies.document.Review;
import com.w4t3rcs.movies.dto.document.ReviewDto;
import com.w4t3rcs.movies.dto.request.ReviewCreationRequest;
import com.w4t3rcs.movies.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
                .orElseThrow(NotFoundException::new);
        return ReviewDto.fromReview(review);
    }

    @Caching(cacheable = {
            @Cacheable(value = "ReviewService::getReviewById", key = "#request.review.id != null ? #request.review.id : 'null'")
    })
    @Transactional
    public ReviewDto createReview(ReviewCreationRequest request) {
        final Review review = reviewRepository.save(request.getReview());
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(request.getImdbId()))
                .apply(new Update().push("reviewIds", review))
                .first();
        return ReviewDto.fromReview(review);
    }

    @Caching(put = {
            @CachePut(value = "ReviewService::getReviewById", key = "#id")
    })
    @Transactional
    public ReviewDto updateReview(ObjectId id, String body) {
        final Review review = reviewRepository.save(new Review(id, body));
        return ReviewDto.fromReview(review);
    }

    @CacheEvict(value = "ReviewService::getReviewById", key = "#id")
    @Transactional
    public ReviewDto deleteReview(ObjectId id) {
        final Review review = reviewRepository.findById(id).orElseThrow(NotFoundException::new);
        reviewRepository.delete(review);
        final Movie movie = movieRepository.findByReviewId(id).orElseThrow(NotFoundException::new);
        movie.getReviewIds().remove(review);
        movieRepository.save(movie);
        return ReviewDto.fromReview(review);
    }
}
