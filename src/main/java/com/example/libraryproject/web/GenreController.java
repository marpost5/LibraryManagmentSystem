package com.example.libraryproject.web;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Genre;
import com.example.libraryproject.model.Movie;
import com.example.libraryproject.service.BookService;
import com.example.libraryproject.service.GenreService;
import com.example.libraryproject.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    private final BookService bookService;
    private final MovieService movieService;

    public GenreController(GenreService genreService, BookService bookService, MovieService movieService) {
        this.genreService = genreService;
        this.bookService = bookService;
        this.movieService = movieService;
    }
    @GetMapping({""})
    public String showGenres(Model model) {
        List<Genre> genres;
        genres=this.genreService.findAllGenres();
        model.addAttribute("genres",genres);
        model.addAttribute("bodyContent","genres");
        return "master-template.html";
    }
    @GetMapping("/{id}/edit-genre")
    public String editGenrePage(@PathVariable Long id, Model model) {
        if (this.genreService.findGenreById(id)!=null) {
            Genre genre=this.genreService.findGenreById(id);
            model.addAttribute("genre", genre);
            model.addAttribute("bodyContent", "add-genre");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping({"/add-genre"})
    public String showAdd(Model model) {
        model.addAttribute("bodyContent","add-genre");
        return "master-template.html";
    }
    @PostMapping("/add")
    public String saveGenre(
            @RequestParam(required = false) Long id,
            @RequestParam String name) {
        if (id != null) {
            this.genreService.updateGenre(id,name);
        } else {
            this.genreService.createGenre(name);
        }
        return "redirect:/genres";
    }

    @PostMapping({"/{id}/delete"})
    public String delete(@PathVariable Long id) {
        this.genreService.deleteGenre(id);
        return "redirect:/genres";
    }
}
