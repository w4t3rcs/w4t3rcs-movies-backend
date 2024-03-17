package com.w4t3rcs.movies.dto.request;

import com.w4t3rcs.movies.dto.document.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ReviewRequest {
    private String imdbId;
    private ReviewDto reviewDto;
}
