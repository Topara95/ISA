function citaj() {		
		$.ajax({
			 url: "../api/culturalVenues/getCinemas",
			 method: "GET",
			 success: function(data){
				cinemas(data);
			 },
			 error: function(){
				 alert("Error while getting cinemas!");
			 }
		});	
}

function cinemas(data) {
	var cin = sessionStorage.getItem("idIzmjenaCinema");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, cinema) {
		if(cinema.id==cin) {
			$('#name').val(cinema.name);
			$('#description').val(cinema.description);
			$('#address').val(cinema.address);
		}
	});
	
}

function promjeni() {
	var id = sessionStorage.getItem("idIzmjenaCinema");
	$.ajax({
		type : 'PUT',
		url : "../api/culturalVenues/"+id,
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON2(),
		success : function(data) {
			if(data==null) {
				alert("Neuspjesno!");
			}else {
				//alert("promjeniiiii");
				window.location.href = "cinemaList.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			window.location.href = "cinemaList.html";
		}
	});
	
}

function formToJSON2() {
	return JSON.stringify({
		"name" : $('#name').val(),
		"description" : $('#description').val(),
		"address" : $('#address').val(),		
	});
}
