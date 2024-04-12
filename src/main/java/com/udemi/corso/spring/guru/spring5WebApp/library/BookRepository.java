package com.udemi.corso.spring.guru.spring5WebApp.library;

import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {
}
