function props(){
	$(".welcome").empty();
	console.log("kliknuo");
	$.ajax({
		  method : 'GET',
		  url : "../api/props",
		  success : function(data){
			  console.log("uspjesno!");
			  podijeliOglase(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});
}

function podijeliOglase(data) {
	console.log("usao u podjelu");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, oglas) {
	console.log(oglas.name);
	$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
			</div>`);
	});
}
	
$(document).on("click",".fanzoneButton",function(){
	$(".welcome").empty();
	$(".welcome").append(`<img class="card-img-top" id="welcome" src="images/cheerblue.png" alt="">`);
});