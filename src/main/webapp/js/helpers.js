function hideMessages() {
  $('.alert').remove();
}

function showErrors(list) {
  var alertDanger = $('<div />').addClass('alert alert-danger'),
    items = list.map(function (err) {
      return '<li>' + err + '</li>';

    }).join('');

  alertDanger.append('<ul>' + items + '</ul>');

  $('form').prepend(alertDanger);
}

function showSuccess() {
  $('<div />')
    .addClass('alert alert-success')
    .html('Salvo com sucesso!')
    .prependTo('form');
}

function showNote(msg){
  $('<div />')
    .addClass('alert alert-info')
    .html(msg)
    .prependTo('form');
}

function serialize(form){
  return $('input, select', form)
    .toArray()
    .reduce(function(obj, elem){
      if(elem.name){
        obj[elem.name] = $(elem).val();
      }
      return obj;
    },{});
}
