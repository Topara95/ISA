function ucitaj() {
	var vrsta = sessionStorage.getItem("vrtsaVenue");
	if(vrsta == "cinema") {
		$("#izbor").append(`<option>DRAMA</option>
		<option name="CRIME">CRIME</option>
		<option name="THRILLER">THRILLER</option>
		<option name="SCIFI">SCIFI</option>
		<option name="COMEDY">COMEDY</option>
		<option name="MYSTERY">MYSTERY</option>`);
		var venue = sessionStorage.getItem("idIzmjenaCinema");
	} else {
		$("#izbor").append(`<option>OPERA</option>`);
		var venue = sessionStorage.getItem("idIzmjenaTheater");
	}
	
	$.ajax({
		 url: "../api/culturalVenues",
		 method: "GET",
		 success: function(data){
			 var cinemas = data;
			 var list = cinemas == null ? [] : (cinemas instanceof Array ? cinemas : [ cinemas ]);
			 $.each(list, function(index, cinema) {
				 if(cinema.id == venue) {
					 sessionStorage.setItem("eventCulVenue",JSON.stringify(cinema));
				 }
			 });
		 },
		 error: function(){
			 alert("Error while getting cinemas!");
		 }
	});
}

function kreiraj() {
	var vrsta = sessionStorage.getItem("vrtsaVenue");
	if(vrsta == "cinema") {
		var venue = sessionStorage.getItem("idIzmjenaCinema");
		var tip = "MOVIE";
	} else {
		var venue = sessionStorage.getItem("idIzmjenaTheater");
		var tip = "PLAY";
	}
	var cinema = JSON.parse(sessionStorage.getItem("eventCulVenue"));
	
	$.ajax({
	type : 'POST',
	url : "../api/events",
	contentType : 'application/json',
	dataType : "json",
	data:formToJSON7(cinema,tip),
	success : function(data) {
		if(data==null) {
			alert("Neuspjesno!");
		}else {
			var data1 = data;
			if(data.eventType=="MOVIE") {
				window.location.href = "cinemaList.html";
			} else {
				window.location.href = "theaterList.html";
			}
		}
	},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		//window.location.href = "cinemaList.html";
	}
});
	
}

function formToJSON7(data,tip) {
	return JSON.stringify({
		"name" : $('#name').val(),
		"actors" : $('#actors').val(),
		"genre" : $("#izbor option:selected").attr('name'),
		"director" : $('#director').val(),
		"duration" : $('#duration').val(),
		"poster" : $('#poster').val(),
		"description" : $('#description').val(),
		"culturalVenue" : data,
		"averageRating" : 0,
		"eventType" : tip
		
	});
}