// form submit
$('form').on('submit', function (e) {
  e.preventDefault();

  hideMessages();

  // transform in a 'single' object, with key:value :
  // {name: '', email: ''}
  var data = serialize(this);

  var disableValidation = $('#disableValidation').is(':checked');

  // validate
  var err = disableValidation ? [] : getErrors(data);

  if(err.length){
    showErrors(err);
    showNote('Errors by client');

  } else {
    // send to server
    $.ajax({
      url: 'api/users',
      type: 'POST',
      dataType: 'json',
      contentType: "application/json",
      data: JSON.stringify(data),
      success: showSuccess,
      error: function (res) {
        showErrors(JSON.parse(res.responseText));
        showNote('Errors by server');
      }
    });
  }

});
