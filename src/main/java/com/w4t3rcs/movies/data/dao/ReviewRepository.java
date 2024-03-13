package com.w4t3rcs.movies.data.dao;

import com.w4t3rcs.movies.data.document.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
