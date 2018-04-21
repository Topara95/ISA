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
	$.ajax({
		 url: "../api/events",
		 method: "GET",
		 success: function(data1){
			 var list1 = data1 == null ? [] : (data1 instanceof Array ? data1 : [ data1 ]);
			 $.each(list1, function(index, event) {
				 	if(cin == event.culturalVenue.id) {
				 		$('#spisakEventC').append(`<li><b>`+event.name+`<label style="margin-left:15px">Average grade:`+event.averageRating+`</label></b>
				 		<a style="margin-left:20px;background-color:red" id="delete`+event.id+`" onclick="deleteEvent(this)" class="btn">Delete</a>
				 		<a style="margin-left:20px;background-color:yellow" id="edit`+event.id+`" onclick="editEvent(this)" class="btn">Edit</a></li>`);
				 		
				 	}
				 	
			 });
		 },
		 error: function(){
			 alert("Error while getting cinemas!");
		 }
	});	
	
}

function editEvent(obj) {
	var id1 = obj.id;
	var id = id1.split("edit")[1];
	sessionStorage.setItem("idIzmjenaEvent",id);
	window.location.href="editEvent.html";
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

function prebaciMe(obj){
	var id1 = obj.id;
	sessionStorage.setItem("vrtsaVenue",id1);
	window.location.href = "createEvent.html";
}

function deleteEvent(obj){
	var id1 = obj.id;
	var id = id1.split("delete")[1];
		$.ajax({
			method : 'GET',
			url : "../api/events/delete/"+id,
			success : function(data){
				console.log("uspjesno!");
				window.location.href = 'editTheater.html';
			},
			error: function(){
				console.log("neuspesno");
			}
		});
}
