package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Genre;
import com.example.libraryproject.model.Movie;

import java.util.List;
import java.util.Set;

public interface BookService {
    List<Book> listAllBooks();
    Book findById(Long Id);
    Book findByTitle(String title);
    Book findByAuthor(String author);
    Book create(String title, String author, String publisher, Set<Genre> genres, Movie movie);
    Book update(Long id, String title, String author, String publisher,Set<Genre> genres,Movie movie);
    Book delete(Long id);

}
