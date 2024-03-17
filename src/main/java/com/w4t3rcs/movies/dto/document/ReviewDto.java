package com.w4t3rcs.movies.dto.document;

import com.w4t3rcs.movies.document.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ReviewDto implements Serializable {
    private ObjectId id;
    private String body;

    public static ReviewDto fromReview(Review review) {
        return new ReviewDto(review.getId(), review.getBody());
    }

    public Review toReview() {
        return new Review(this.getId(), this.getBody());
    }
}
