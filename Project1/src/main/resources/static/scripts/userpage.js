var loggeduser = JSON.parse(sessionStorage.getItem('loggedUser'));
var edit_url="../api/users/"+loggeduser.id

$(document).on('submit','.editform', function(e) {
	e.preventDefault();
	var p = $('#password').val();
	var cp = $('#password-confirm').val();
	if(p == cp){
		$.ajax({
			type : 'PUT',
			url : edit_url,
			contentType : 'application/json',
			dataType : "json",
			data:formToJSON(),
			success : function(data) {
				location.reload();
				sessionStorage.setItem('loggedUser',JSON.stringify(data));
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("AJAX ERROR: " + errorThrown+"signin");
			}
		});
	}else{
		alert("Passwords must match");
	}
});


function formToJSON() {
	return JSON.stringify({
	"id":loggeduser.id,
	"email":loggeduser.email,
    "name":$('#name').val(),
    "surname":$('#surname').val(),
    "city":$('#city').val(),
    "phone":$('#phone').val(),
    "password":$('#password').val()
	});
}

function generateUserInfo(){
	document.getElementById("name").value = loggeduser.name;
	document.getElementById("surname").value = loggeduser.surname;
	document.getElementById("city").value = loggeduser.city;
	document.getElementById("phone").value = loggeduser.phone;
	document.getElementById("password").value = loggeduser.password;
	document.getElementById("password-confirm").value = loggeduser.password;
}