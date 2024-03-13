package com.w4t3rcs.movies.data.dao;

import com.w4t3rcs.movies.data.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findByImdbId(String imdbId);

    Optional<Movie> findByTitle(String title);
}
