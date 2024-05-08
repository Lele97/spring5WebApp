package com.udemi.corso.spring.guru.spring5WebApp.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfermationToken , Long> {

    Optional<ConfermationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfermationToken c " +
            "SET c.confermedDateTime = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
