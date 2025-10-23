package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    // Show list of books
    @GetMapping
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books"; // Thymeleaf template: books.html
    }

    // Show form to add a book
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book"; // Thymeleaf template: add-book.html
    }

    // Handle form submission
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book); // save book into DB
        return "redirect:/library"; // redirect to list page
    }

    // Health check endpoint
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
