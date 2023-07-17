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

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        Publisher publisherSecond = new Publisher();
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driver Design", "123123");
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");

        publisher.setName("Agency");
        publisher.setAddreessLine1("Adress test line 12");
        publisher.setCity("Naples");
        publisher.setState("Italy");
        publisher.setZip("80299");

        publisherSecond.setName("Agency2");
        publisherSecond.setAddreessLine1("Adress test line 24");
        publisherSecond.setCity("Rome");
        publisherSecond.setState("Italy");
        publisherSecond.setZip("80222");

        eric.getBook().add(ddd);
        ddd.getAuthors().add(eric);
        publisher.getBooks().add(ddd);
        ddd.setPublisher(publisher);

        rod.getBook().add(noEJB);
        noEJB.getAuthors().add(rod);
        publisherSecond.getBooks().add(noEJB);
        noEJB.setPublisher(publisherSecond);

        publisherRepository.save(publisher);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisherSecond);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Number of Authors:  " + authorRepository.count());
        System.out.println("Number of Books:  " + bookRepository.count());
        System.out.println("Number of Publisher:  " + publisherRepository.count());
    }
}
