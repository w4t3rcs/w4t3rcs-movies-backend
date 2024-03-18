package com.w4t3rcs.movies.service.data;

import com.w4t3rcs.movies.dao.GenreRepository;
import com.w4t3rcs.movies.document.Genre;
import com.w4t3rcs.movies.dto.document.GenreDto;
import com.w4t3rcs.movies.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(GenreDto::fromGenre)
                .toList();
    }

    @Cacheable(value = "GenreService::getGenreByName", key = "#name")
    @Transactional(readOnly = true)
    public GenreDto getGenreByName(String name) {
        final Genre genre = genreRepository.findByName(name)
                .orElseThrow(NotFoundException::new);
        return GenreDto.fromGenre(genre);
    }
}
