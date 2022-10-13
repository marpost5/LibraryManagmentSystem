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
import java.util.Set;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final BookService bookService;
    private final GenreService genreService;

    public MovieController(MovieService movieService, BookService bookService, GenreService genreService) {
        this.movieService = movieService;
        this.bookService = bookService;
        this.genreService = genreService;
    }
    @GetMapping({""})
    public String showMovies(Model model) {
        List<Movie> movies;
        movies=this.movieService.listMovies();
        model.addAttribute("movies",movies);
        model.addAttribute("bodyContent","movies");
        return "master-template.html";
    }
    @GetMapping({"/add-movie"})
    public String showAdd(Model model) {
        model.addAttribute("bodyContent","add-movie");
        model.addAttribute("books",this.bookService.listAllBooks());
        model.addAttribute("genres",this.genreService.findAllGenres());
        return "master-template.html";
    }
    @PostMapping("/add-movie")
    public String saveMovie(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam int length,
            @RequestParam String director,
            @RequestParam (required = false)Book book,
            @RequestParam Set<Genre> genres) {
        if (id != null) {
            this.movieService.update(id, title,length,director,book,genres);
        } else {
            this.movieService.create(title,length,director,book,genres);
        }
        return "redirect:/movies";
    }
    @GetMapping("/{id}/edit-movie")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id)!=null) {
            Movie movie=this.movieService.findById(id);
            model.addAttribute("movie", movie);
            model.addAttribute("books",this.bookService.listAllBooks());
            model.addAttribute("genres",this.genreService.findAllGenres());
            model.addAttribute("bodyContent", "add-movie");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @PostMapping({"/{id}/delete"})
    public String delete(@PathVariable Long id) {
        this.movieService.delete(id);
        return "redirect:/movies";
    }
}
