package com.udemi.corso.spring.guru.spring5WebApp.controller;

import com.udemi.corso.spring.guru.spring5WebApp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String getBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());

        return "books/list";
    }
}
