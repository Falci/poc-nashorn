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
