function citaj() {		
		$.ajax({
			 url: "../api/culturalVenues/getTheaters",
			 method: "GET",
			 success: function(data){
				theaters(data);
			 },
			 error: function(){
				 alert("Error while getting theaters!");
			 }
		});	
}

function theaters(data) {
	var cin = sessionStorage.getItem("idIzmjenaTheater");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, theater) {
		if(theater.id==cin) {
			$('#name').val(theater.name);
			$('#description').val(theater.description);
			$('#address').val(theater.address);
		}
	});
	
}

function promjeni() {
	var id = sessionStorage.getItem("idIzmjenaTheater");
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
				window.location.href = "theaterList.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
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
