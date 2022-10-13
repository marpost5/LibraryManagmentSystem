package com.example.libraryproject.service.impl;

import com.example.libraryproject.model.Genre;
import com.example.libraryproject.repository.GenreRepository;
import com.example.libraryproject.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    @Override
    public List<Genre> findAllGenres() {
        return this.genreRepository.findAll();
    }

    @Override
    public Genre findGenreById(Long id) {
        return this.genreRepository.findById(id).orElseThrow();
    }

    @Override
    public Genre createGenre(String name) {
    Genre genre=new Genre(name);
    return this.genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Long id,String name) {
        Genre genre=this.findGenreById(id);
        genre.setId(id);
        genre.setName(name);
        return this.genreRepository.save(genre);

    }

    @Override
    public Genre deleteGenre(Long id) {
        Genre genre=this.findGenreById(id);
        this.genreRepository.delete(genre);
        return genre;

    }
}
