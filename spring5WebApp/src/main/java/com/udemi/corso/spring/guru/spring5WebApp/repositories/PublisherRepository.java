package com.udemi.corso.spring.guru.spring5WebApp.repositories;

import com.udemi.corso.spring.guru.spring5WebApp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;


public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
