package com.gary.event.Listener;

import com.gary.event.OnRegistrationSuccessEvent;
import com.gary.persistence.entity.User;
import com.gary.service.UserService;
import com.gary.web.util.SendEmailTLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {

    @Autowired
    private UserService userService ;

    @Autowired
    private SendEmailTLS emailService ;

    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {
            this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationSuccessEvent event){
        User user = event.getUser() ;
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipient = user.getEmail() ;
        String subject = "Registration Confirmation" ;
        String url = event.getAppUrl() + "/register/confirmRegistration?token=" + token;
        String message = "Thank you for registering. Please click on the below link to activate your account.\n";
        emailService.sendSimpleEmail(recipient, subject, message + "http://localhost:8080" + url);
        // System.out.println("\n\n step 7 .\n");
    }
}
