package com.w4t3rcs.movies;

import com.w4t3rcs.movies.dao.GenreRepository;
import com.w4t3rcs.movies.dao.MovieRepository;
import com.w4t3rcs.movies.document.Genre;
import com.w4t3rcs.movies.document.Movie;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@RequiredArgsConstructor
@SpringBootTest
public class DatabaseTest {
    private final ApplicationContext applicationContext;

    @Test
    public void testDb() {
        Genre genre = new Genre();
        genre.setName("Adventure");
        applicationContext.getBean(GenreRepository.class).save(genre);
        MovieRepository bean = applicationContext.getBean(MovieRepository.class);
        Optional<Movie> m3GAN = bean.findByTitle("M3GAN");
        Movie movie = m3GAN.orElseThrow();
        System.out.println(movie);
    }
}
