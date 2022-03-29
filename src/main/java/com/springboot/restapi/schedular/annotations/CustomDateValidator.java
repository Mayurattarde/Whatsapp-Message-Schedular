package com.springboot.restapi.schedular.annotations;

import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomDateValidator implements
ConstraintValidator<CustomDateConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
            LocalDateTime.parse(value);
        } catch (Exception e) {
            return false;
        }
        return true;

}
}
	