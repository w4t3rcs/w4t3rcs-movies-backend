package com.w4t3rcs.movies.dto.document;

import com.w4t3rcs.movies.document.Genre;
import com.w4t3rcs.movies.document.Movie;
import com.w4t3rcs.movies.document.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
public class MovieDto implements Serializable {
    private ObjectId id;
    private String imdbId;
    private String title;
    private LocalDate releaseDate;
    private String trailerLink;
    private String poster;
    private List<Genre> genres;
    private List<String> backdrops;
    private List<Review> reviewIds;

    public static MovieDto fromMovie(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .imdbId(movie.getImdbId())
                .title(movie.getTitle())
                .releaseDate(movie.getReleaseDate())
                .trailerLink(movie.getTrailerLink())
                .poster(movie.getPoster())
                .genres(movie.getGenres())
                .backdrops(movie.getBackdrops())
                .reviewIds(movie.getReviewIds())
                .build();
    }

    public Movie toMovie() {
        return Movie.builder()
                .id(this.getId())
                .imdbId(this.getImdbId())
                .title(this.getTitle())
                .releaseDate(this.getReleaseDate())
                .trailerLink(this.getTrailerLink())
                .poster(this.getPoster())
                .genres(this.getGenres())
                .backdrops(this.getBackdrops())
                .reviewIds(this.getReviewIds())
                .build();
    }
}
