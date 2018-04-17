$(document).on('submit','.sifra', function(e) {
	e.preventDefault();
	var stara = $('#oldPass').val();
	var nova = $('#newPass').val();
	$.ajax({
		type : 'PUT',
		url : "../api/users/firstTimeChangePass/"+nova+"/"+stara,
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			console.log("promjenjeno!")
			window.location.href='index.html';
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown+"signin");
		}
	});
});