#Proof of Concept: Nashorn validation
Using [Oracle Nashorn](http://www.oracle.com/technetwork/articles/java/jf14-nashorn-2126515.html) to apply the same validation in both side (client and server)

## How it works
The same Javascript used to validate the form in client-side is used by Oracle Nashorn to validate the data in server side.

## Validator
This is the Javascript file used to validate the form data:

[`/js/client-and-server.js`](/src/main/webapp/js/client-and-server.js)

```js
function getErrors(form) {

  var errors = [];

  if(!form.name.length){
    errors.push('Name is mandatory');
  }

  if(!form.email.length){
    errors.push('Email is mandatory');

  } else {

    if(!/@falci\.me$/.test(form.email)){
      errors.push('Email must be @falci.me');
    }

  }

  return errors;
}

```

## FormData
Each UI form has a Java Object equivalent:
```java
@ValidateWith("/js/client-and-server.js")
public class UserForm implements FormData {

  private String name;
  private String email;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

}
```

The [`@ValidateWith`](/src/main/java/me/falci/tcc/poc/javascript/annotation/ValidateWith.java) annotation sets the same js file used in UI.

## Nashorn
The [`JsValidator.java`](src/main/java/me/falci/tcc/poc/javascript/JsValidator.java) is able to instantiate the Nashorn and load the Javascript file. Afterwards, it uses the Nashorn's engine to create an implementation of [`Validator.java`](/src/main/java/me/falci/tcc/poc/javascript/Validator.java). It means: **a Java interface implemented by the Javascript file**.
```java
ScriptEngineManager engineManager = new ScriptEngineManager();
ScriptEngine engine = engineManager.getEngineByName("nashorn");
Invocable invocable = (Invocable) engine;

// ...
engine.eval(new FileReader(path));

return invocable.getInterface(Validator.class);
```

##  Object type convertion
The Javascript's array used in the validator's return is automatically transformed in a java.util.List. It means the service can use the Javascript arrays as a List, without additional code.

```java
List<String> errors = JsValidator.getValidator(data)
    .getErrors(data); // javascript function

if(!errors.isEmpty()){
  throw new InvalidFormDataException(errors);
}
```
