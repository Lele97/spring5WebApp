package com.udemi.corso.spring.guru.spring5WebApp.registration;

import com.udemi.corso.spring.guru.spring5WebApp.appuser.AppUserService;
import com.udemi.corso.spring.guru.spring5WebApp.mail.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "api/v1/change")
@AllArgsConstructor
public class ChangePassword {

    private EmailSender emailSender;

    private AppUserService appUserService;

    @GetMapping()
    public String change() {
        return "registration/passwordReset";
    }

    @GetMapping(value = "/password/psw")
    public ResponseEntity<String> changePsw(@RequestParam("psw") String password, @RequestParam("email") String email) {
        appUserService.changePswByUsername(email,password);
        return new ResponseEntity<>("password cambiata con successo "+password+email, HttpStatus.OK);
    }

    @GetMapping(value = "/psw")
    public ResponseEntity<String> submitemailchangepsw() {
        String link = "http://localhost:8080/api/v1/change/password/psw?psw=psw123&email=gabriele.grandinetti@hotmail.com";
        emailSender.send("gabriele.grandinetti@hotmail.com", buildVerificationEmailTemplate(link));
        return new ResponseEntity<>("Inviata email per il cambio della password", HttpStatus.OK);
    }

    public static String buildVerificationEmailTemplate(String link) {
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
                "        <h2 style=\"color: #333;\">Hello,  Gabriele !</h2>\n" +
                "        <p>Welcome to our platform. Please change your password by clicking the link below:</p>\n" +
                "        <p><a href=\"" + link + "\" style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none;\">Verify Email</a></p>\n" +
                "        <p style=\"font-size: 12px; color: #666;\">If you didn't create an account with us, you can safely ignore this email.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
