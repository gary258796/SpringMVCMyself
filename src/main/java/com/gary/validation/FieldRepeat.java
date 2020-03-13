package com.gary.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = FieldRepeatValidator.class)
public @interface FieldRepeat {


    String id() default "id";
    String field();

    String message() default "repeat！";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
