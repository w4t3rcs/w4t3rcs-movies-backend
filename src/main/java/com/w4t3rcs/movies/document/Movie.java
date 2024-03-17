package com.w4t3rcs.movies.document;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Document("movies")
public class Movie implements Serializable {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private LocalDate releaseDate;
    private String trailerLink;
    private String poster;
    @DocumentReference(lookup = "{ 'name' : ?#{#target} }")
    private List<Genre> genres;
    private List<String> backdrops;
    @DocumentReference
    private List<Review> reviewIds;
}
