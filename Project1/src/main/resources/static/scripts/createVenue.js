$(document).on('submit','.venue', function(e) {
	e.preventDefault();
	var type = sessionStorage.getItem("createVenueType");
	console.log(type);
	$.ajax({
		type : 'POST',
		url : "../api/culturalVenues",
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON(type),
		success : function(data) {
			if(data==null) {
				alert("Neuspjesno!");
			}else {
				window.location.href = "index.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
});

function formToJSON(data) {
	console.log("stigaoo");
	return JSON.stringify({
		"name" : $('#name').val(),
		"description" : $('#description').val(),
		"address" : $('#address').val(),
		"cvtype" : data
	});
}