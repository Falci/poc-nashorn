package me.falci.tcc.poc.services;

import java.util.List;

import me.falci.tcc.poc.exception.InvalidFormDataException;
import me.falci.tcc.poc.exception.InvalidJsValidatorException;
import me.falci.tcc.poc.forms.UserForm;
import me.falci.tcc.poc.javascript.JsValidator;

public class UserService {

	public void save(UserForm data) throws InvalidFormDataException, InvalidJsValidatorException {
		
		List<String> errors = JsValidator.getValidator(data)
				.getErrors(data); // javascript function
		
		if(!errors.isEmpty()){
			throw new InvalidFormDataException(errors);
		}
		
		// save
	}

}
