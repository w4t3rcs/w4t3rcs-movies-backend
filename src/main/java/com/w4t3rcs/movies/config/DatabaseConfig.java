package com.w4t3rcs.movies.config;

import com.w4t3rcs.movies.dao.GenreRepository;
import com.w4t3rcs.movies.document.Genre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.List;

@Configuration
public class DatabaseConfig {
    @Bean
    public CommandLineRunner commandLineRunner(GenreRepository genreRepository) {
        return args -> {
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
        };
    }

    @Bean
    public RedisConnectionFactory connectionFactory(@Value("${spring.data.redis.password}") String password) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPassword(password);
        return new JedisConnectionFactory(configuration);
    }
}
