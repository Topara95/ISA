login_url="../api/users/login"

$(document).on('submit','.form-signin', function(e) {
	e.preventDefault();
	$.ajax({
		type : 'POST',
		url : login_url,
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON(),
		success : function(data) {
			if(data != null){
			sessionStorage.setItem('loggedUser',JSON.stringify(data));
			window.location.href='index.html';
			}else{
				
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown+"signin");
		}
	});
});


function formToJSON() {
	return JSON.stringify({
    "email":$('#inputEmail').val(),
    "password":$('#inputPassword').val()
	});
}
