package com.example.libraryproject.service.impl;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Genre;
import com.example.libraryproject.model.Movie;
import com.example.libraryproject.repository.BookRepository;
import com.example.libraryproject.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long Id) {
        return this.bookRepository.findById(Id).orElseThrow();
    }

    @Override
    public Book findByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }

    @Override
    public Book findByAuthor(String author) {
        return this.bookRepository.findByAuthor(author);
    }

    @Override
    public Book create(String title, String author, String publisher, Set<Genre> genres,Movie movie) {
        Book book=new Book(title,author,publisher,genres,movie);
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String author, String publisher, Set<Genre> genres, Movie movie) {
        Book book=this.findById(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenres(genres);
        book.setMovie(movie);
        return this.bookRepository.save(book);

    }

    @Override
    public Book delete(Long id) {
        Book book=this.findById(id);
        this.bookRepository.delete(book);
        return book;
    }

}
