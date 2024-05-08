package com.udemi.corso.spring.guru.spring5WebApp.registration;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Level;

@Controller
@AllArgsConstructor
@Log
@RequestMapping(name = "/")
public class HomeController {

    @GetMapping
    public String homePage(){
        log.log(Level.INFO, "HomePage");
        return "home/homepage";
    }
}
