package com.w4t3rcs.movies.controller;

import com.w4t3rcs.movies.dto.document.GenreDto;
import com.w4t3rcs.movies.service.data.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1.0/genres")
@RestController
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{name}")
    public ResponseEntity<GenreDto> getGenreByName(@PathVariable String name) {
        return ResponseEntity.ok(genreService.getGenreByName(name));
    }
}
