package com.example.libraryproject.service.impl;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Genre;
import com.example.libraryproject.model.Movie;
import com.example.libraryproject.repository.BookRepository;
import com.example.libraryproject.repository.MovieRepository;
import com.example.libraryproject.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return this.movieRepository.findById(id).orElseThrow();
    }

    @Override
    public Movie findByTitle(String title) {
        return this.movieRepository.findByTitle(title);
    }

    @Override
    public Movie create(String title, int length, String director, Book book, Set<Genre> genres) {
        Movie movie=new Movie(title,length,director,book,genres);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, String title, int length, String director,Book book,Set<Genre> genres) {
        Movie movie=this.findById(id);
        movie.setTitle(title);
        movie.setLength(length);
        movie.setDirector(director);
        movie.setBook(book);
        movie.setGenres(genres);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movie=this.findById(id);
        this.movieRepository.delete(movie);
        return movie;
    }

}
