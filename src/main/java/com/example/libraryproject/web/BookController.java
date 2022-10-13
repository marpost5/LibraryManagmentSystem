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
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final MovieService movieService;
    private final GenreService genreService;

    public BookController(BookService bookService, MovieService movieService, GenreService genreService) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.genreService = genreService;
    }
    @GetMapping({""})
    public String showBooks(Model model) {
        List<Book> books;
        books=this.bookService.listAllBooks();
        model.addAttribute("books",books);
        model.addAttribute("bodyContent","books");
        return "master-template.html";
    }

    @GetMapping("/{id}/edit-book")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id)!=null) {
            Book book=this.bookService.findById(id);
            model.addAttribute("book", book);
            model.addAttribute("movies",this.movieService.listMovies());
            model.addAttribute("genres",this.genreService.findAllGenres());
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping({"/add-book"})
    public String showAdd(Model model) {
        model.addAttribute("bodyContent","add-book");
        model.addAttribute("movies",this.movieService.listMovies());
        model.addAttribute("genres",this.genreService.findAllGenres());
        return "master-template.html";
    }
    @PostMapping("/add")
    public String saveBook(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String publisher,
            @RequestParam Set<Genre> genres,
            @RequestParam(required = false) Movie movie) {
        if (id != null) {
            this.bookService.update(id, title, author,publisher,genres,movie);
        } else {
            this.bookService.create(title,author,publisher,genres,movie);
        }
        return "redirect:/books";
    }

    @PostMapping({"/{id}/delete"})
    public String delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return "redirect:/books";
    }

}
