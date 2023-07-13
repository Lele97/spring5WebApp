package com.udemi.corso.spring.guru.spring5WebApp.repositories;

import com.udemi.corso.spring.guru.spring5WebApp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
