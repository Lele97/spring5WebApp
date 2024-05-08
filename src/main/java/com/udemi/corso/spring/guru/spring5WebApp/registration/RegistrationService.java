package com.udemi.corso.spring.guru.spring5WebApp.registration;

import com.udemi.corso.spring.guru.spring5WebApp.appuser.AppUser;
import com.udemi.corso.spring.guru.spring5WebApp.appuser.AppUserRole;
import com.udemi.corso.spring.guru.spring5WebApp.appuser.AppUserService;
import com.udemi.corso.spring.guru.spring5WebApp.mail.EmailSender;
import com.udemi.corso.spring.guru.spring5WebApp.registration.token.ConfermationToken;
import com.udemi.corso.spring.guru.spring5WebApp.registration.token.ConfermationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private AppUserService appUserService;
    private EmailValidator emailValidator;
    private ConfermationTokenService confirmationTokenService;

    private EmailSender emailSender;


    public String register(RegistrationRequest request) throws IllegalStateException {
        boolean isValid = emailValidator.test(request.getEmail());

        if (!isValid)
            throw new IllegalStateException("email ko");

        String token = appUserService.signUpUser(new AppUser(
                request.getFristName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER));

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(), buildVerificationEmailTemplate(request.getFristName(), link));
        return token;
    }

    public String confirmToken(String token) {

        ConfermationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfermedDateTime() != null)
            throw new IllegalStateException("email already confirmed");


        if(confirmationToken.getExiredDateTime().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("token expired");

        confirmationTokenService.setConfirmedAt(token);

        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
        return "confirmed";

    }

    public static String buildVerificationEmailTemplate(String name, String link) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Email Verification</title>\n" +
                "</head>\n" +
                "<body style=\"font-family: Arial, sans-serif;\">\n" +
                "    <div style=\"max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ccc;\">\n" +
                "        <h2 style=\"color: #333;\">Hello, " + name + "!</h2>\n" +
                "        <p>Welcome to our platform. Please verify your email address by clicking the link below:</p>\n" +
                "        <p><a href=\"" + link + "\" style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none;\">Verify Email</a></p>\n" +
                "        <p style=\"font-size: 12px; color: #666;\">If you didn't create an account with us, you can safely ignore this email.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
