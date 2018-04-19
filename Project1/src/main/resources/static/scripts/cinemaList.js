var cinemas_url = "../api/culturalVenues/getCinemas"

	function getCinemas(){
	$.ajax({
		 url: cinemas_url,
		 method: "GET",
		 success: function(data){
			 $(".cinemasTable").empty();
			 for(i=0;i<data.length;i++){
				 $(".cinemasTable").append(`<tr>
                              <td><span class="font-weight-bold">`+data[i].name+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].address+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].description+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].averageGrade+`</span></td>
                              <td><button onclick="editID(this)" id=`+data[i].id+` type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#exampleModalR">Edit</button></td>
                              
                          </tr>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting cinemas!");
		 }
	});
}

function editID(data) {
	var id = data.id;
	sessionStorage.setItem("idIzmjenaCinema",id);
	window.location.href="editCinema.html";
}

function editCinema(id) {
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
				window.location.href = "cinemaList.html";
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

