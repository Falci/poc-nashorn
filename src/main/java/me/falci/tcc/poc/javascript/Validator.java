package me.falci.tcc.poc.javascript;

import java.util.List;

import me.falci.tcc.poc.forms.FormData;

public interface Validator {

	public List<String> getErrors(FormData data);
	
}
