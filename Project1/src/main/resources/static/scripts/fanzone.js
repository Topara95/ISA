function onload() {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	if(user.usertype != "FANZONEADMIN") {
		document.getElementById("onHold").hidden = "";
	} else {
		document.getElementById("onHold").hidden = "hidden";
	}
}

function myProps() {
	/*$(".welcome").empty();
	$.ajax({
		  method : 'GET',
		  url : "../api/props",
		  success : function(data){
			  console.log("uspjesno!");
			  podijeliOglaseNaCekanju(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});	*/
}

function propsOnHold() {
	$(".welcome").empty();
	//console.log("kliknuo");
	$.ajax({
		  method : 'GET',
		  url : "../api/props",
		  success : function(data){
			  console.log("uspjesno!");
			  podijeliOglaseNaCekanju(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});	
}

function podijeliOglaseNaCekanju(data) {
	//console.log("usao u podjelu");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, oglas) {
	//console.log(oglas.name);
	if(oglas.approved == false) {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button onclick="odobriOglas(this)" type="button" class="btn btn-success rekvizitButtoni" id="odobri`+oglas.id+`" name="odobri`+oglas.id+`">Approve</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Decline and delete</button></div>
				</div>`);
	}
	});
}

function odobriOglas(data) {
	var pokusaj = data.name;
	console.log("odobravam");
	var konacan = pokusaj.split("odobri")[1];
	$(document).on("click",data,function(e) {
		e.preventDefault();
		//console.log("pokusaj na "+pokusaj);
		//console.log("konacan "+konacan);		
		$.ajax({
			method : 'GET',
			url : "../api/props/approve/"+konacan,
			success : function(data){
				console.log("uspjesno!");
				window.location.href = 'fanzone.html';
			},
			error: function(){
				console.log("neuspesno");
			}
		});
		
	});
}
//thematicProps
function props() {
	$(".welcome").empty();
	$(".welcome").append(`<div><select id="culVenues" class="form-control"></select></div><br>`);
	$(".welcome").append(`<div id="type"><label><input type="radio" id="USED" name="type" value="USED">Used</label>
    							<label><input type="radio" id="NEW" name="type" value="NEW">New</label></div><br>
    					  <div><button onclick="props1()" type="button" class="btn btn-success izbrisi">Submit</button></div>`);
	$("#USED").attr('checked', true);
	readVenues();
}
//my thematic props
function myProps() {
	$(".welcome").empty();
	$(".welcome").append(`<div><select id="culVenues" class="form-control"></select></div><br>`);
	$(".welcome").append(`<div id="type"><label><input type="radio" id="USED" name="type" value="USED">Used</label>
    							<label><input type="radio" id="NEW" name="type" value="NEW">New</label></div><br>
    					  <div><button onclick="props2()" type="button" class="btn btn-success izbrisi">Submit</button></div>`);
	$("#USED").attr('checked', true);
	readVenues();
}
//ajax poziv za pozorista/bioskope
function readVenues() {
	$.ajax({
		  method : 'GET',
		  url : "../api/culturalVenues",
		  success : function(data){
			  //console.log("uspjesno!");
			  podijeliObjekte(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});
}
//punjenje comboboxa
function podijeliObjekte(data) {
	//console.log("usao u podjelu");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, culVe) {
	$("#culVenues").append(`<option name=`+culVe.id+` value=`+culVe.id+`>`+culVe.name+`</option>`);
	});
}
//thematicProps
function props1(){
	var culValID = $('#culVenues option:selected').attr('name');
	var tptype = $('input[name=type]:checked').val();
	props();
	$("#"+tptype).attr('checked', true);
	$('#culVenues select').val(culValID);
	$.ajax({
		  method : 'GET',
		  url : "../api/props/"+culValID+"/"+tptype,
		  success : function(data){
			  //console.log("uspjesno!");
			  podijeliOglase(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});
}
//myProps
function props2(){
	var culValID = $('#culVenues option:selected').attr('name');
	var tptype = $('input[name=type]:checked').val();
	myProps();
	$("#"+tptype).attr('checked', true);
	$('#culVenues select').val(culValID);
	$.ajax({
		  method : 'GET',
		  url : "../api/props/my/"+culValID+"/"+tptype,
		  success : function(data){
			// console.log("uspjesno!");
			  podijeliOglase(data);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});
}

function podijeliOglase(data) {
	//console.log("usao u podjelu oglasa");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, oglas) {
	//console.log(oglas.name);
	if(oglas.tptype == "NEW" && oglas.approved == true) {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:green"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.name+`">Make an offer</button>
   					<button onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>`);
	} else if(oglas.tptype == "USED" && oglas.approved == true) {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.name+`">Make an offer</button>
   					<button onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>`);
	}
		});
	console.log("zavrsio!");
}

function izmjeniOglas(obj) {
	var pokusaj = obj.name;
	//console.log("pokusaj na "+pokusaj);
	var konacan = pokusaj.split("izmjeni")[1];
	$(document).on("click",obj,function(e) {
		e.preventDefault();		
		$.ajax({
			method : 'GET',
			url : "../api/props",
			success : function(data){
				console.log("uspjesno!");
				nadjiOglas(data,konacan);
			},
			error: function(){
				console.log("neuspesno");
			}
		});
		
	});
}

function nadjiOglas(data,kod) {
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, oglas) {
	console.log(oglas.name);
	if(oglas.id == kod) {
		sessionStorage.setItem('mijenjanje',JSON.stringify(oglas));
		sessionStorage.setItem('tipRekvizit',"edit");
		window.location.href = 'thematicProps.html';
	}
	});
}

function izbrisiOglas(obj){
	var pokusaj = obj.name;
	//console.log("pokusaj na "+pokusaj);
	var konacan = pokusaj.split("izbrisi")[1];
	$(document).on("click",obj,function(e) {
		e.preventDefault();
		//console.log("pokusaj na "+pokusaj);
		//console.log("konacan "+konacan);		
		$.ajax({
			method : 'GET',
			url : "../api/props/delete/"+konacan,
			success : function(data){
				console.log("uspjesno!");
				window.location.href = 'fanzone.html';
			},
			error: function(){
				console.log("neuspesno");
			}
		});
		
	});
}
	
$(document).on("click",".fanzoneButton",function(){
	$(".welcome").empty();
	$(".welcome").append(`<img class="card-img-top" id="welcome" src="images/cheerblue.png" alt="">`);
});

$(document).on("click",".createThematicProps",function(){
	sessionStorage.setItem('tipRekvizit',"new");
	window.location.href = 'thematicProps.html';
});