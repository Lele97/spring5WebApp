package com.udemi.corso.spring.guru.spring5WebApp.controller;

import com.udemi.corso.spring.guru.spring5WebApp.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String getAuthor(Model model) {

        model.addAttribute("authors", authorRepository.findAll());

        return "author/list";
    }

}
