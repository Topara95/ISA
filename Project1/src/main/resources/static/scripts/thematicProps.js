function citaj() {
	var tip = sessionStorage.getItem("tipRekvizit");
	var oglas = JSON.parse(sessionStorage.getItem("mijenjanje"));
	$("#culturalVenueSelect").empty();
	$.ajax({
		  method : 'GET',
		  async : false,
		  url : "../api/culturalVenues",
		  success : function(data){
			  console.log("uspjesno!");
			  podijeli(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});
	if(tip == "edit") {
		$("#naslov").empty();
		$("#naslov").append(`Edit thematic prop`);
		$("#naziv").val(oglas.name);
		$("#opis").val(oglas.description);
		$("#slikaRekvizit").val(oglas.picture);
		$("#datum").val(oglas.date);
		$("#tipRekvizit").val(oglas.tptype);
		$("#culturalVenueSelect").val(oglas.culturalVenueId);
		document.getElementById("dugmeNapravi").hidden = "hidden";
		document.getElementById("dugmeIzmjeni").hidden = "";
		
	}else {
		$("#naslov").empty();
		$("#naslov").append(`Create thematic prop`);
		document.getElementById("dugmeNapravi").hidden = "";
		document.getElementById("dugmeIzmjeni").hidden = "hidden";
	}
	
}

function podijeli(data) {
	console.log("usao u podjelu");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, culVe) {
	$("#culturalVenueSelect").append(`<option id=`+culVe.id+` name=`+culVe.id+` value=`+culVe.id+`>`+culVe.name+`</option>`);
	});
}

$(document).on('click','#dugmeNapravi',function(e) {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	$.ajax({
		type : 'POST',
		url : "../api/props",
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON(user),
		success : function(data) {
			if(data==null) {
				alert("Neuspjesno!");
			}else {
				window.location.href = "fanzone.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
});

$(document).on('click','#dugmeIzmjeni',function(e) {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	var oglas = JSON.parse(sessionStorage.getItem("mijenjanje"));
	$.ajax({
		type : 'PUT',
		url : "../api/props/"+oglas.id,
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON2(user),
		success : function(data) {
			if(data==null) {
				alert("Neuspjesno!");
			}else {
				window.location.href = "fanzone.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
});

function formToJSON2(user) {
	return JSON.stringify({
		"name" : $('#naziv').val(),
		"description" : $('#opis').val(),
		"date" : $('#datum').val(),
		"tptype" : $('#tipRekvizit').val(),
		"picture" : $('#slikaRekvizit').val(),
		"culturalVenueId" : $('#culturalVenueSelect option:selected').attr('name')
	});
}

function formToJSON(user) {
	return JSON.stringify({
		"name" : $('#naziv').val(),
		"description" : $('#opis').val(),
		"date" : $('#datum').val(),
		"tptype" : $('#tipRekvizit').val(),
		"picture" : $('#slikaRekvizit').val(),
		"createdBy" : user.id,
		"reserved" : "NO",
		"approved" : false,
		"culturalVenueId" : $('#culturalVenueSelect option:selected').attr('name')
	});
}