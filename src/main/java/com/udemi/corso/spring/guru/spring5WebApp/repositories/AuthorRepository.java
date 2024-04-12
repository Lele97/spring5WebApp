package com.udemi.corso.spring.guru.spring5WebApp.repositories;

import com.udemi.corso.spring.guru.spring5WebApp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
