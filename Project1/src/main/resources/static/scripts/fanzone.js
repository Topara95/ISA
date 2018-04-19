function onload() {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	if(user.usertype == "FANZONEADMIN") {
		document.getElementById("onHold").hidden = "";
		console.log("usao u if");
	} else {
		console.log("usao u else");
		document.getElementById("onHold").hidden = "hidden";
	}
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
		if(oglas.picture!="") {
		$(".welcome").append(`<div style="text-align:center"><h2>Thematic props on hold</h2></div><br>
				<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><img src="/images/cinema.jpg" style="width:120px;height:100px;padding-right:15px;padding-bottom:10px"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button onclick="odobriOglas(this)" type="button" class="btn btn-success rekvizitButtoni" id="odobri`+oglas.id+`" name="odobri`+oglas.id+`">Approve</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Decline and delete</button></div>
				</div>`);
		} else {
			$(".welcome").append(`<div style="text-align:center"><h2>Thematic props on hold</h2></div><br>
					<div class="panel panel-default form-group" id="pojedinacni">
		 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
		 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
		 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
		 			<div id="batoni">
	   					<button onclick="odobriOglas(this)" type="button" class="btn btn-success rekvizitButtoni" id="odobri`+oglas.id+`" name="odobri`+oglas.id+`">Approve</button>
	   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Decline and delete</button></div>
					</div>`);
		}
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
	$(".welcome").append(`<div style="text-align:center"><h2>Thematic props</h2></div><br>
						  <div><select id="culVenues" class="form-control"></select></div><br>`);
	$(".welcome").append(`<div style="text-align:center" id="type"><label><input type="radio" id="USED" name="type" value="USED">Used</label>
    							<label><input type="radio" id="NEW" name="type" value="NEW">New</label></div><br>
    					  <div style="text-align:center"><button onclick="props1()" type="button" class="btn btn-success izbrisi">Submit</button></div>`);
	$("#USED").attr('checked', true);
	readVenues();
}
//my thematic props
function myProps() {
	$(".welcome").empty();
	$(".welcome").append(`<div style="text-align:center"><h2>My thematic props</h2></div><br>
						  <div><select id="culVenues" class="form-control"></select></div><br>`);
	$(".welcome").append(`<div style="text-align:center" id="type"><label><input type="radio" id="USED" name="type" value="USED">Used</label>
    							<label><input type="radio" id="NEW" name="type" value="NEW">New</label></div><br>
    					  <div style="text-align:center"><button onclick="props2()" type="button" class="btn btn-success izbrisi">Submit</button></div>`);
	$("#USED").attr('checked', true);
	readVenues();
}
//ajax poziv za pozorista/bioskope
function readVenues() {
	$.ajax({
		  method : 'GET',
		  async : false,
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
	$("#culVenues").append(`<option id=`+culVe.id+` name=`+culVe.id+` value=`+culVe.id+`>`+culVe.name+`</option>`);
	});
}
//thematicProps
function props1(){
	var culValID = $('#culVenues option:selected').attr('name');
	sessionStorage.setItem("venueID",culValID);
	var tptype = $('input[name=type]:checked').val();
	props();
	$("#"+tptype).attr('checked', true);
	document.getElementById(culValID).selected = true;
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
	sessionStorage.setItem("venueID",culValID);
	var tptype = $('input[name=type]:checked').val();
	myProps();
	$("#"+tptype).attr('checked', true);
	document.getElementById(culValID).selected = true;
	$.ajax({
		  method : 'GET',
		  url : "../api/props/my/"+culValID+"/"+tptype,
		  success : function(data){
			// console.log("uspjesno!");
			  podijeliMojeOglase(data);
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
		if(oglas.picture!="") {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><img src="/images/cinema.jpg" style="width:120px;height:100px;padding-right:15px;padding-bottom:10px"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:green"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button onclick="ponudiPonude(this)" type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.id+`">Make an offer</button>
   					<button hidden="hidden" onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
   					<button hidden="hidden" onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>
				<div style="margin-left:20px"><textarea hidden="hidden" style="width:100px" id="money`+oglas.id+`" class="form-control" rows="1"></textarea>
				<button hidden="hidden" style="margin-top:5px" onclick="posaljiPonudu(this)" type="button" class="btn btn-success" id="posalji`+oglas.id+`">Accept</button></div>
				<div class="comment-container" id="ponuda1`+oglas.id+`"><ul id="listaPonuda1`+oglas.id+`" class="comments-list"></ul></div>`);
		} else {
			$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
		 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
		 			<label id="odmakniMe" style="color:green"><b>`+oglas.tptype+`</b><label></div>
		 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
		 			<div id="batoni">
	   					<button onclick="ponudiPonude(this)" type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.id+`">Make an offer</button>
	   					<button hidden="hidden" onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
	   					<button hidden="hidden" onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
					</div>
					<div style="margin-left:20px"><textarea hidden="hidden" style="width:100px" id="money`+oglas.id+`" class="form-control" rows="1"></textarea>
					<button hidden="hidden" style="margin-top:5px" onclick="posaljiPonudu(this)" type="button" class="btn btn-success" id="posalji`+oglas.id+`">Accept</button></div>
					<div class="comment-container" id="ponuda1`+oglas.id+`"><ul id="listaPonuda1`+oglas.id+`" class="comments-list"></ul></div>`);
		}
	} else if(oglas.tptype == "USED" && oglas.approved == true) {
		if(oglas.picture!="") {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><img src="/images/cinema.jpg" style="width:120px;height:100px;padding-right:15px;padding-bottom:10px"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button onclick="ponudiPonude(this)" type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.id+`">Make an offer</button>
   					<button hidden="hidden" onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
   					<button hidden="hidden" onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>
				<div style="margin-left:20px"><textarea hidden="hidden" style="width:100px" id="money`+oglas.id+`" class="form-control" rows="1"></textarea>
				<button hidden="hidden" style="margin-top:5px" onclick="posaljiPonudu(this)" type="button" class="btn btn-success" id="posalji`+oglas.id+`">Accept</button></div>
				<div class="comment-container" id="ponuda1`+oglas.id+`"><ul id="listaPonuda1`+oglas.id+`" class="comments-list"></ul></div>`);
		}else {
			$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
		 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
		 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
		 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
		 			<div id="batoni">
	   					<button onclick="ponudiPonude(this)" type="button" class="btn btn-success rekvizitButtoni" name="ponudi`+oglas.id+`">Make an offer</button>
	   					<button hidden="hidden" onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
	   					<button hidden="hidden" onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
					</div>
					<div style="margin-left:20px"><textarea hidden="hidden" style="width:100px" id="money`+oglas.id+`" class="form-control" rows="1"></textarea>
					<button hidden="hidden" style="margin-top:5px" onclick="posaljiPonudu(this)" type="button" class="btn btn-success" id="posalji`+oglas.id+`">Accept</button></div>
					<div class="comment-container" id="ponuda1`+oglas.id+`"><ul id="listaPonuda1`+oglas.id+`" class="comments-list"></ul></div>`);
		}
	}
		});
	console.log("zavrsio!");
}

function posaljiPonudu(obj) {
	var pokusaj = obj.id;
	var id = pokusaj.split("posalji")[1];
	//alert(id);
	//sessionStorage.setItem("rekvizitPonuda",id);
	$.ajax({
		type : 'POST',
		url : "../api/offer",
		contentType : 'application/json',
		dataType : "json",
		data:addPromjena(id),
		success : function(data) {
			if(data==null) {
				alert("Neuspjesno!");
			}else {
				window.location.href = "fanzone.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//alert("Postoji Vasa ponuda! Imate mogucnost promjene postojece.")
		}
	});
}

function addPromjena(id) {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	var venue = JSON.parse(sessionStorage.getItem("venueID"));
	//var id1 = sessionStorage.getItem("rekvizitPonuda");
	//alert(id +" "+ user.id +" "+ $('#money'+id).val() +" "+ venue);
	return JSON.stringify({
		"propId" : id,
		"createdBy" : user.id,
		"offeredMoney" : $('#money'+id).val(),
		"culturalVenueId" : venue
	});
}


function podijeliMojeOglase(data) {
	//console.log("usao u podjelu oglasa");
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, oglas) {
	//console.log(oglas.name);
	if(oglas.tptype == "NEW" && oglas.approved == true) {
		if(oglas.picture!="") {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><img src="/images/cinema.jpg" style="width:120px;height:100px;padding-right:15px;padding-bottom:10px"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:green"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button onclick="dajPonudu(this)" type="button" class="btn btn-success rekvizitButtoni" name="daj`+oglas.id+`">Offers</button>
   					<button onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>
				<div class="comment-container" id="ponuda`+oglas.id+`"><ul id="listaPonuda`+oglas.id+`" class="comments-list"></ul></div>`);
		} else {
			$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
		 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
		 			<label id="odmakniMe" style="color:green"><b>`+oglas.tptype+`</b><label></div>
		 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
		 			<div id="batoni">
	   					<button onclick="dajPonudu(this)" type="button" class="btn btn-success rekvizitButtoni" name="daj`+oglas.id+`">Offers</button>
	   					<button onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
	   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
					</div>
					<div class="comment-container" id="ponuda`+oglas.id+`"><ul id="listaPonuda`+oglas.id+`" class="comments-list"></ul></div>`);
		}
	} else if(oglas.tptype == "USED" && oglas.approved == true) {
		if(oglas.picture!="") {
		$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
	 			<div id="divNaziv" class="panel-heading"><img src="/images/cinema.jpg" style="width:120px;height:100px;padding-right:15px;padding-bottom:10px"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
	 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
	 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
	 			<div id="batoni">
   					<button onclick="dajPonudu(this)" type="button" class="btn btn-success rekvizitButtoni" name="daj`+oglas.id+`">Offers</button>
   					<button onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
				</div>
				<div class="comment-container" id="ponuda`+oglas.id+`"><ul id="listaPonuda`+oglas.id+`" class="comments-list"></ul></div>`);
		}else {
			$(".welcome").append(`<div class="panel panel-default form-group" id="pojedinacni">
		 			<div id="divNaziv" class="panel-heading"><label id="odmakniMe"><b>`+oglas.name+`</b><br><label>
		 			<label id="odmakniMe" style="color:yellow"><b>`+oglas.tptype+`</b><label></div>
		 			<div id="divOpis" class="panel-body"><textarea readonly id="divOpis2" class="form-control" rows="6">`+oglas.description+`</textarea></div>
		 			<div id="batoni">
	   					<button onclick="dajPonudu(this)" type="button" class="btn btn-success rekvizitButtoni" name="daj`+oglas.id+`">Offers</button>
	   					<button onclick="izmjeniOglas(this)" type="button" class="btn btn-warning izbrisi" id="izmjeni`+oglas.id+`" name="izmjeni`+oglas.id+`">Edit</button>
	   					<button onclick="izbrisiOglas(this)" type="button" class="btn btn-danger izbrisi" name="izbrisi`+oglas.id+`">Delete</button></div>
					</div>
					<div class="comment-container" id="ponuda`+oglas.id+`"><ul id="listaPonuda`+oglas.id+`" class="comments-list"></ul></div>`);
		}
	}
		});
	console.log("zavrsio!");
}

function ponudiPonude(obj) {
	var pokusaj = obj.name;
	var id = pokusaj.split("ponudi")[1];
	sessionStorage.setItem("rekvizitPonuda",id);
	document.getElementById("money"+id).hidden = "";
	document.getElementById("posalji"+id).hidden = "";
	//alert(id);
	$.ajax({
		method : 'GET',
		url : "../api/offer/"+id,
		success : function(data){
			console.log("uspjesno!");
			podijeliPonude1(data,id);
		},
		error: function(){
			console.log("neuspesno");
		}
	});
}

function dajPonudu(obj) {
	var pokusaj = obj.name;
	var id = pokusaj.split("daj")[1];	
		$.ajax({
			method : 'GET',
			url : "../api/offer/"+id,
			success : function(data){
				console.log("uspjesno!");
				podijeliPonude(data,id);
			},
			error: function(){
				console.log("neuspesno");
			}
		});

}


function podijeliPonude(data,id) {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$("#listaPonuda"+id).empty();
	$.each(list, function(index, oglas) {
		$("#listaPonuda"+id).append(`<li style="margin-top:5px" id ="jednaPonuda">			
					<div class="comment-box" >
						<div class="comment-head">
							<div class="comment-name><h6 by-author"><b>`+oglas.createdBy+`</b></h6><label style="margin-left:50px"><b>Offer: `+oglas.offeredMoney+` RSD</b></label>
								<button style="margin-left:100px" type="button" class="btn btn-success" id="prihvati`+oglas.id+`">Accept</button>
							</div>							
						</div>
					</div>
				</li>`);
		
	});
}

function podijeliPonude1(data,id) {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$("#listaPonuda1"+id).empty();
	$.each(list, function(index, oglas) {
		if(oglas.createdBy!=user.id) {
		$("#listaPonuda1"+id).append(`<li style="margin-top:5px" id ="jednaPonuda">			
					<div class="comment-box" >
						<div class="comment-head">
							<div class="comment-name><h6 by-author"><b>`+oglas.createdBy+`</b></h6><label style="margin-left:50px"><b>Offer: `+oglas.offeredMoney+` RSD</b></label>
							</div>							
						</div>
					</div>
				</li>`);
		} else {
			$("#listaPonuda1"+id).append(`<li style="margin-top:5px" id ="jednaPonuda">			
					<div class="comment-box" >
						<div class="comment-head">
							<div class="comment-name><h6 by-author"><b>`+oglas.createdBy+`</b></h6><label style="margin-left:50px"><b>Offer: `+oglas.offeredMoney+` RSD</b></label>
							<textarea id="vrijednost`+oglas.id+`" style="margin-left:10px;width:100px" class="form-control" rows="1"></textarea>
							<button style="margin-left:20px" onclick="promjenaPonude(this)" type="button" class="btn btn-warning" id="promjeniPonudu`+oglas.id+`">Edit</button>
							</div>							
						</div>
					</div>
				</li>`);
		}
		
	});
}

function promjenaPonude(obj) {
	var pokusaj = obj.id;
	//console.log("pokusaj na "+pokusaj);
	var id = pokusaj.split("promjeniPonudu")[1];
	$.ajax({
		type : 'PUT',
		url : "../api/offer",
		contentType : 'application/json',
		dataType : "json",
		data:editPromjena(id),
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
}

function editPromjena(id) {
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	var id1 = sessionStorage.getItem("rekvizitPonuda");
	//alert($('#vrijednost'+id).val());
	return JSON.stringify({
		"propId" : id1,
		"createdBy" : user.id,
		"offeredMoney" : $('#vrijednost'+id).val()
	});
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