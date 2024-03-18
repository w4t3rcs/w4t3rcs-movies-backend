package com.w4t3rcs.movies.service.data;

import com.w4t3rcs.movies.dao.MovieRepository;
import com.w4t3rcs.movies.document.Movie;
import com.w4t3rcs.movies.dto.document.MovieDto;
import com.w4t3rcs.movies.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieDto::fromMovie)
                .toList();
    }

    @Cacheable(value = "MovieService::getMovieByImdbId", key = "#id")
    @Transactional(readOnly = true)
    public MovieDto getMovieByImdbId(String id) {
        final Movie movie = movieRepository.findByImdbId(id)
                .orElseThrow(NotFoundException::new);
        return MovieDto.fromMovie(movie);
    }
}
