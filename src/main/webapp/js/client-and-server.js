function getErrors(form) {

  var errors = [];

  if(!form.name.length){
    errors.push('Nome é obrigatório');
  }

  if(!form.email.length){
    errors.push('Email é obrigatório');

  } else {

    if(!/@falci\.me$/.test(form.email)){
      errors.push('Email precisa ser @falci.me');
    }
    
  }

  return errors;
}
