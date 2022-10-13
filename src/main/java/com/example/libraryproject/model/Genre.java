package com.example.libraryproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Book> books;
    @OneToMany
    private List<Movie> movies;
    public Genre()
    {

    }
    public Genre(String name)
    {
        this.name=name;
        this.books=new ArrayList<>();
        this.movies=new ArrayList<>();
    }


}
