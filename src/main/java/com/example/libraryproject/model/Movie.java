package com.example.libraryproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int length;
    private String director;
    @ManyToMany
    private Set<Genre> genres;
    @OneToOne
    private Book book;
    public Movie() {

    }
    public Movie(String title, int length,String director) {
        this.title = title;
        this.length = length;
        this.director=director;
        this.genres=new HashSet<>();
    }
    public Movie(String title,int length,String director,Book book,Set<Genre> genres)
    {
        this.title = title;
        this.length = length;
        this.director=director;
        this.book=book;
        this.genres=genres;
    }


}
