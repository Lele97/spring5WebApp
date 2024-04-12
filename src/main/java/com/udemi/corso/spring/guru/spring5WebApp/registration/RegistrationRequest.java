package com.udemi.corso.spring.guru.spring5WebApp.registration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String fristName;
    private final String lastName;
    private final String email;
    private final String password;
}
