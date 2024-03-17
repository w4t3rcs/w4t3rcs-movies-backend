package com.w4t3rcs.movies.dao;

import com.w4t3rcs.movies.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findByImdbId(String imdbId);

    Optional<Movie> findByTitle(String title);

    @Query("{reviewIds: ?0}")
    Optional<Movie> findByReviewId(@Param("id") ObjectId id);
}
