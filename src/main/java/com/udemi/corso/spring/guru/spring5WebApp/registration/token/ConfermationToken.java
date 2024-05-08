package com.udemi.corso.spring.guru.spring5WebApp.registration.token;

import com.udemi.corso.spring.guru.spring5WebApp.appuser.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class ConfermationToken {

    @Id
    @SequenceGenerator(name = "confirmation_token_suquence", allocationSize = 1, sequenceName = "confirmation_token_suquence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_suquence")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime exiredDateTime;

    private LocalDateTime confermedDateTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private AppUser appUser;

    public ConfermationToken(String token, LocalDateTime createdDateTime, LocalDateTime exiredDateTime, AppUser appUser) {
        this.token = token;
        this.createdDateTime = createdDateTime;
        this.exiredDateTime = exiredDateTime;
        this.appUser = appUser;
    }


}
