package com.w4t3rcs.movies.data.dao;

import com.w4t3rcs.movies.data.document.Genre;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, ObjectId> {
    Optional<Genre> findByName(String name);
}
