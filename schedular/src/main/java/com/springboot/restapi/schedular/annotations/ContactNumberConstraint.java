package com.springboot.restapi.schedular.annotations;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//import com.fasterxml.jackson.core.util.RequestPayload;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD})
@Constraint(validatedBy = ContactNumberValidator.class)
public @interface ContactNumberConstraint {
    //error message
    String message() default "Invalid contact number";
    //represents group of constraints
    Class<?>[] groups() default {};
    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};
}