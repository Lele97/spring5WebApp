package com.udemi.corso.spring.guru.spring5WebApp.appuser;

import com.udemi.corso.spring.guru.spring5WebApp.registration.token.ConfermationToken;
import com.udemi.corso.spring.guru.spring5WebApp.registration.token.ConfermationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ConfermationTokenService confermationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG.formatted(email)));
    }

    public void changePswByUsername(String email, String password) {

        System.out.println(email);

        AppUser appUser = appUserRepository.getByEmail(email);

        if (appUser == null)
            throw new IllegalStateException("User not exist");

        appUser.setPassword(bCryptPasswordEncoder.encode(password));

        appUserRepository.save(appUser);
    }


    public String signUpUser(AppUser appUser) {
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExist)
            throw new IllegalStateException("email already taken");

        String pass = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(pass);
        appUserRepository.save(appUser);

        UUID token = UUID.randomUUID();
        ConfermationToken confermationToken = new ConfermationToken(
                token.toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confermationTokenService.saveConfirmationToken(confermationToken);

        return confermationToken.getToken();
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }
}
