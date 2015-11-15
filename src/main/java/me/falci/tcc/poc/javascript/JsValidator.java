package me.falci.tcc.poc.javascript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import me.falci.tcc.poc.exception.InvalidJsValidatorException;
import me.falci.tcc.poc.forms.UserForm;
import me.falci.tcc.poc.javascript.annotation.ValidateWith;

public class JsValidator {

	public static Validator getValidator(UserForm data) throws InvalidJsValidatorException {

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Invocable invocable = (Invocable) engine;

		ValidateWith annotation = data.getClass()
				.getAnnotation(ValidateWith.class);
		
		String path = Optional.ofNullable(annotation)
				.orElseThrow(InvalidJsValidatorException::new)
				.value();
		
		try {
			engine.eval(new FileReader(Validator.class.getResource("/../.." + path).getFile()));
		} catch (FileNotFoundException | ScriptException e) {
			
			throw new InvalidJsValidatorException();
		}

		return invocable.getInterface(Validator.class);
	}

}
