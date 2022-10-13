package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Genre;
import com.example.libraryproject.model.Movie;

import java.util.List;
import java.util.Set;

public interface MovieService {
    List<Movie> listMovies();
    Movie findById(Long id);
    Movie findByTitle(String title);
    Movie create(String title, int length, String director, Book book, Set<Genre> genres);
    Movie update(Long id,String title,int length,String director,Book book,Set<Genre> genres);
    Movie delete(Long id);
}
