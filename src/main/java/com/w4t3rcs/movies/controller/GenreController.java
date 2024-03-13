package com.w4t3rcs.movies.controller;

import com.w4t3rcs.movies.data.dao.GenreRepository;
import com.w4t3rcs.movies.data.document.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/v1.0/genres")
@RestController
public class GenreController {
    private final GenreRepository genreRepository;

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Genre> getGenreByName(@PathVariable String name) {
        Optional<Genre> genreOptional = genreRepository.findByName(name);
        return genreOptional.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
