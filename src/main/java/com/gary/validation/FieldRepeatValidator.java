package com.gary.validation;

import com.gary.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;

public class FieldRepeatValidator implements ConstraintValidator<FieldRepeat, Object> {

    @Autowired
    private UserService userService ;

    private Logger logger = Logger.getLogger(getClass().getName());


    private String id ;
    private String field;
    private String message ;

    @Override
    public void initialize(FieldRepeat fieldRepeat) {
        this.id = fieldRepeat.id() ;
        this.field = fieldRepeat.field();
        this.message = fieldRepeat.message() ;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        logger.info("\n <<< field : " + field + "\n") ;
        logger.info("\n <<< Object : " + o + "\n") ;

        String userName = (String) o ;
        logger.info("\n <<< userName : " + userName + "\n") ;

        if ( userService.findByUserName(userName) != null) {
            // 有這個username的用戶了
            message = "already exists!" ;
            return false ;
        }else {
            message = "Username available!" ;
            return true ;
        }
    }

}
