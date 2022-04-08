package com.springboot.restapi.schedular.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.i18n.phonenumbers.PhoneNumberUtil;

public class ContactNumberValidator implements 
ConstraintValidator<ContactNumberConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
		try {
			PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
			return phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(value, "IN"));
		} catch (Exception e) {
			return false;
		}
	}	 
}