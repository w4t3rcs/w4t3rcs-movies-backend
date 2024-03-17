package com.w4t3rcs.movies.dto.request;

import com.w4t3rcs.movies.document.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ReviewCreationRequest {
    private String imdbId;
    private Review review;
}
