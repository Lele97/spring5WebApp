package com.udemi.corso.spring.guru.spring5WebApp.bootstrap;

import com.udemi.corso.spring.guru.spring5WebApp.domain.Author;
import com.udemi.corso.spring.guru.spring5WebApp.domain.Book;
import com.udemi.corso.spring.guru.spring5WebApp.domain.Publisher;
import com.udemi.corso.spring.guru.spring5WebApp.repositories.AuthorRepository;
import com.udemi.corso.spring.guru.spring5WebApp.repositories.BookRepository;
import com.udemi.corso.spring.guru.spring5WebApp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driver Design","123123");
        eric.getBook().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","3939459459");
        rod.getBook().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books:  " + bookRepository.count());

        Publisher agency = new Publisher("3256 Epiphenomenal Avenue\n" +
                "Minneapolis","Minneapolis","MN","55416");


        publisherRepository.save(agency);

        Publisher second_agency = new Publisher("3256 Epiphenomenal Avenue\n" +
                "Minneapolis","Minneapolis","MN","55416");

        publisherRepository.save(second_agency);

        System.out.println("Number of Publisher:  " + publisherRepository.count());

    }

}
