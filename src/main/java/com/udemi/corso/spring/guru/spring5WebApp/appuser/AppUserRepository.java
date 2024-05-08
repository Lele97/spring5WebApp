package com.udemi.corso.spring.guru.spring5WebApp.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("Select a from AppUser a where a.email = ?1")
    AppUser getByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = true " +
            "WHERE a.email = ?1")
    int enableAppUser(String email);

}
