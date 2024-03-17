package com.w4t3rcs.movies.dao;

import com.w4t3rcs.movies.document.Genre;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, ObjectId> {
    Optional<Genre> findByName(String name);
}
