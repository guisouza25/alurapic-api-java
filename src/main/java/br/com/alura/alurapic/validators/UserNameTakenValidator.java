package br.com.alura.alurapic.validators;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.alura.alurapic.service.UserService;

public class UserNameTakenValidator implements ConstraintValidator<UserNameTaken, String> {

    //private String message;
	
	@Inject
	UserService service;

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	
    	Boolean isTaken = service.isUserNameTaken(object);
    	Boolean isValid;
    			
    	if(isTaken) {
    		isValid = false;
    	} else {
    		isValid = true;
    	}   
    	return isValid;
    }
}