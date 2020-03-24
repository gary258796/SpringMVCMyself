package com.gary.util;

import com.gary.entity.Message;
import com.gary.entity.User;
import com.gary.service.UserService;
import com.gary.validation.ValidEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class Validate {

    @Autowired
    private UserService userService ;

    private Logger logger = Logger.getLogger(getClass().getName());


    public void updateValidate(User user, int id, Errors errors) {

        User sqlUser = userService.findById(id);

        logger.info("\n >>> check email same as self or not \n");

        if( userService.isUserEmailExistExceptSelf(sqlUser.getEmail(), user.getEmail()) ){
            logger.info("\n >>> Email have been used! \n");
            errors.rejectValue("email", "useremail.exist");
        }

        logger.info("\n >>> email is user's original one ! \n");
    }

    //check msg
    public void messageValidate(Message message, Errors errors){
        if(message.getMessage() != ""){ // not empty
            if(message.getMessage().length() < 255){

            }else{
                errors.rejectValue("message", "message.required");
            }
        }else{
            ValidationUtils.rejectIfEmpty(errors, "message", "message.required ");
        }
    }

}
