package me.falci.tcc.poc.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidFormDataException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private final List<String> errors = new ArrayList<String>();
	
	public InvalidFormDataException(List<String> errors) {
		
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		
		return errors;
	}

}
