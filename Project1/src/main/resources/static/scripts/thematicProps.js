function citaj() {
	var tip = sessionStorage.getItem("tipRekvizit");
	var oglas = JSON.parse(sessionStorage.getItem("mijenjanje"));
	if(tip == "edit") {
		$("#naziv").val(oglas.name);
		$("#opis").val(oglas.description);
		$("#slikaRekvizit").val(oglas.picture);
		$("#datum").val(oglas.date);
		$("#tipRekvizit").val(oglas.tptype);
		document.getElementById("dugmeNapravi").hidden = "hidden";
		document.getElementById("dugmeIzmjeni").hidden = "";	
	}else {
		document.getElementById("dugmeNapravi").hidden = "";
		document.getElementById("dugmeIzmjeni").hidden = "hidden";
	}
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
	});
}

function formToJSON(user) {
	return JSON.stringify({
		"name" : $('#naziv').val(),
		"description" : $('#opis').val(),
		"date" : $('#datum').val(),
		"tptype" : $('#tipRekvizit').val(),
		"picture" : $('#slikaRekvizit').val(),
		"createdBy" : user.name,
		"reserved" : "NO",
		"approved" : false
	});
}