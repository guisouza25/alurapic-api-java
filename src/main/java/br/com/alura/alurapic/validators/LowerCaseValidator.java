package br.com.alura.alurapic.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LowerCaseValidator implements ConstraintValidator<LowerCase, String> {

    //private String message;

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	
    	Boolean isValid;
    	
        if ( object != null && object.matches("[a-z0-9]{2,}")) {
        	isValid = true;
        } else {
        	isValid = false;
        }
       
        return isValid;
    }
}