package com.example.libraryproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    @ManyToMany
    private Set<Genre> genres;
    @OneToOne
    private Movie movie;


    public Book() {

    }

    public Book(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genres = new HashSet<>();

    }
    public Book(String title, String author, String publisher, Set<Genre> genres,Movie movie) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.movie=movie;
        this.genres=genres;

    }

    public Set<Genre> getGenres() {
        return genres;
    }

}
