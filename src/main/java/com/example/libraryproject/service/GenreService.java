package com.example.libraryproject.service;

import com.example.libraryproject.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAllGenres();

    Genre findGenreById(Long id);

    Genre createGenre(String name);

    Genre updateGenre(Long id,String name);

    Genre deleteGenre(Long id);

}
