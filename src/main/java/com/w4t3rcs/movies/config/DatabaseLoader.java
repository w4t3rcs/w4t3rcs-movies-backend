package com.w4t3rcs.movies.config;

import com.w4t3rcs.movies.data.dao.GenreRepository;
import com.w4t3rcs.movies.data.document.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseLoader implements CommandLineRunner {
    private final GenreRepository genreRepository;

    @Override
    public void run(String... args) {
        saveGenresToDatabase();
    }

    private void saveGenresToDatabase() {
        genreRepository.deleteAll();
        genreRepository.saveAll(List.of(
                new Genre(null, "Adventure"),
                new Genre(null, "Animation"),
                new Genre(null, "Action"),
                new Genre(null, "Comedy"),
                new Genre(null, "Family"),
                new Genre(null, "Science Fiction"),
                new Genre(null, "Fantasy"),
                new Genre(null, "Drama"),
                new Genre(null, "History"),
                new Genre(null, "Horror")
        ));
    }
}
