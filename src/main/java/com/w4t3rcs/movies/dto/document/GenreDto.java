package com.w4t3rcs.movies.dto.document;

import com.w4t3rcs.movies.document.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
public class GenreDto implements Serializable {
    private ObjectId id;
    private String name;

    public static GenreDto fromGenre(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public Genre toGenre() {
        return new Genre(this.getId(), this.getName());
    }
}
