package com.gary.event.Listener;

import com.gary.event.OnRegistrationSuccessEvent;
import com.gary.persistence.entity.User;
import com.gary.service.UserService;
import com.gary.web.util.SendEmailTLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
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
        System.out.println("\n\n step 3 .\n");
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationSuccessEvent event){
        System.out.println("\n\n step 4 .\n");
        User user = event.getUser() ;
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipient = user.getEmail() ;
        String subject = "Registration Confirmation" ;
        String url = event.getAppUrl() + "/confirmRegistration?token=" + token;
        String message = "Thank you for registering. Please click on the below link to activate your account.";
        emailService.sendSimpleEmail(recipient, subject, message + "http://localhost:8081/garypracticela2_war_exploded/" + url);
    }
}
