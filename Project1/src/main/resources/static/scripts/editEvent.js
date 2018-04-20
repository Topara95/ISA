function citaj() {		
		$.ajax({
			 url: "../api/events",
			 method: "GET",
			 success: function(data){
				events(data);
			 },
			 error: function(){
				 alert("Error while getting cinemas!");
			 }
		});	
}

function events(data) {
	var ev = sessionStorage.getItem("idIzmjenaEvent");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, event) {
		if(event.id==ev) {
			$('#name').val(event.name);
			$('#actors').val(event.actors);
			$('#genre').val(event.genre);
			$('#director').val(event.director);
			$('#duration').val(event.duration);
			$('#poster').val(event.poster);
			$('#averageRating').val(event.averageRating);
			$('#description').val(event.description);
			}
	});
	
	
}

function promjeni() {
	var id = sessionStorage.getItem("idIzmjenaEvent");
	$.ajax({
		type : 'PUT',
		url : "../api/events/"+id,
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON2(),
		success : function(data) {
			var event = data;
			if(data==null) {
				alert("Neuspjesno!");
			}else {
				//alert("promjeniiiii");
				if(event.eventType == "MOVIE") {
					window.location.href = "editCinema.html";
				} else {
					window.location.href = "editTheater.html";
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//window.location.href = "cinemaList.html";
		}
	});
	
}

function formToJSON2() {
	return JSON.stringify({
		"name" : $('#name').val(),
		"actors" : $('#actors').val(),
		"genre" : $('#genre').val(),
		"director" : $('#director').val(),
		"duration" : $('#duration').val(),
		"poster" : $('#poster').val(),
		"averageRating" : $('#averageRating').val(),
		"description" : $('#description').val(),
	});
}