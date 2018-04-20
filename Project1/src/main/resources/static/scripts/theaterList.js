
var theaters_url = "../api/culturalVenues/getTheaters"

function getTheaters(){
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	$.ajax({
		 url: theaters_url,
		 method: "GET",
		 success: function(data){
			 $(".theatersTable").empty();
			 if(user == null) {
				 for(i=0;i<data.length;i++){
					 $(".theatersTable").append(`<tr>
	                              <td><span class="font-weight-bold">`+data[i].name+`</span></td>
	                              <td><span class="font-weight-bold">`+data[i].address+`</span></td>
	                              <td><span class="font-weight-bold">`+data[i].description+`</span></td>
	                              <td><span class="font-weight-bold">`+data[i].averageGrade+`</span></td>
	                              
	                              
	                          </tr>`);
				 }
			 } else if(user.usertype == "VENUEADMIN" ) {
			 for(i=0;i<data.length;i++){
				 $(".theatersTable").append(`<tr>
                              <td><span class="font-weight-bold">`+data[i].name+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].address+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].description+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].averageGrade+`</span></td>
                              <td><button onclick="editID(this)" id=`+data[i].id+` type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#exampleModalR">Edit</button></td>
                              
                          </tr>`);
			 }
			 } else {
				 for(i=0;i<data.length;i++){
					 $(".theatersTable").append(`<tr>
	                              <td><span class="font-weight-bold">`+data[i].name+`</span></td>
	                              <td><span class="font-weight-bold">`+data[i].address+`</span></td>
	                              <td><span class="font-weight-bold">`+data[i].description+`</span></td>
	                              <td><span class="font-weight-bold">`+data[i].averageGrade+`</span></td>
	                              
	                              
	                          </tr>`);
				 }
			 }
		 },
		 error: function(){
			 alert("Error while getting theaters!");
		 }
	});
}

function editID(data) {
	var id = data.id;
	sessionStorage.setItem("idIzmjenaTheater",id);
	window.location.href="editTheater.html";
}

function editTheater(id) {
	$.ajax({
		type : 'PUT',
		url : "../api/props/"+id,
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON2(),
		success : function(data) {
			if(data==null) {
				alert("Neuspjesno!");
			}else {
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