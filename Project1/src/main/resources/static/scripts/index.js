var islogged_url = "../api/users/isLoggedIn"
var logout_url = "../api/users/logout"

function createCV(data) {
	var id = data.id;
	sessionStorage.setItem('createVenueType',id);
	window.location.href = "createVenue.html";
}	

function changeRatings() {
	document.getElementById("goldInput").hidden = "";
	document.getElementById("silverInput").hidden = "";
	document.getElementById("bronzeInput").hidden = "";
	document.getElementById("potvrdaThreshold").hidden = "";
	document.getElementById("editThreshold").hidden = "hidden";	
}

function potvrdaRatings(){
	var g = $('#goldInput').val();	
	var s = $('#silverInput').val();
	var b = $('#bronzeInput').val();
	//alert(g +" "+ s +" "+ b);
	if(g == "GOLD"){
		g = sessionStorage.getItem("gold");
	}
	if(s == "SILVER"){
		s = sessionStorage.getItem("silver");
	}
	if(b == "BRONZE"){
		b = sessionStorage.getItem("bronze");
	}
	$.ajax({
		type : 'PUT',
		url : "../api/threshold",
		contentType : 'application/json',
		dataType : "json",
		data:formToJSON4(g,s,b),
		success : function(data) {
			if(data==null) {
				alert("Neuspjesno!");
			}else {
				window.location.href = "index.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

function formToJSON4(g,s,b) {
	return JSON.stringify({
		"goldThreshold" : g,
		"silverThreshold" : s,
		"bronzeThreshold" : b
	});
}
	
function generateNavbar(){
	var user = JSON.parse(sessionStorage.getItem("loggedUser"));
	if(user!=null) {
	vratiKorisnike();
	$.ajax({
		  method : 'GET',
		  url : "../api/threshold",
		  success : function(data){
			// console.log("uspjesno!");
			  var mt = data;
			  //console.log(mt.goldThreshold + " " + mt.silverThreshold);
			  $("#goldL").append(`<b>`+mt.goldThreshold+`</b>`);
			  $("#silverL").append(`<b>`+mt.silverThreshold+`</b>`);
			  $("#bronzeL").append(`<b>`+mt.bronzeThreshold+`</b>`);
			  sessionStorage.setItem('gold',mt.goldThreshold);
			  sessionStorage.setItem('silver',mt.silverThreshold);
			  sessionStorage.setItem('bronze',mt.bronzeThreshold);
		  },
		  error: function(){
			  console.log("neuspesno");
		  }
		  
	});
	if(user.usertype == "SYSTEMADMIN") {
		document.getElementById("THEATER").hidden = "";
		document.getElementById("CINEMA").hidden = "";
		document.getElementById("editThreshold").hidden = "";
	} else {
		document.getElementById("THEATER").hidden = "hidden";
		document.getElementById("CINEMA").hidden = "hidden";
		document.getElementById("editThreshold").hidden = "hidden";
	}
	}
	 $.ajax({
		 url: islogged_url,
		 method: "GET",
		 success: function(data){
			 var user = data;
			 if(user.email!=null){
				 $(".navitems").empty();
				 $(".navitems").append(`<li class="nav-item active">
	                <a class="nav-link" href="#">Home
	                  <span class="sr-only">(current)</span>
	                </a>
	              </li>
	              <li class="nav-item">
	                <a class="nav-link" href="fanzone.html">FanZone</a>
	              </li>
	              <li class="nav-item">
	                <a class="nav-link" href="#">About</a>
	              </li>
	              <li class="nav-item">
	                <a class="nav-link" href="#">Services</a>
	              </li>
	              <li class="nav-item">
	                <a class="nav-link" href="userpage.html">User `+user.name+` signed in</a>
	              </li>
	              <li class="nav-item">
	                <a class="nav-link" href="index.html" onclick="logout()">Sign out</a>
	              </li>`);
			 }else{
			    	$(".navitems").empty();
			    	$(".navitems").append(`<li class="nav-item active">
			                <a class="nav-link" href="#">Home
			                  <span class="sr-only">(current)</span>
			                </a>
			              </li>
			              <li class="nav-item">
			                <a class="nav-link" href="#">About</a>
			              </li>
			              <li class="nav-item">
			                <a class="nav-link" href="register.html">Register</a>
			              </li>
			              <li class="nav-item">
			                <a class="nav-link" href="signin.html">Sign in</a>
			              </li>`);
			 }
	        
	    },
	    error:function(data){
	    	
	    }
	 });
}

function vratiKorisnike(){
	 $("#spisak").empty();
	 $.ajax({
	  type : 'GET',
	  url : "../api/users",
	  success : podjeliKorisnike
	 });
}

function podjeliKorisnike(data){
	 var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	 $("#spisak").empty();
	 $.each(list, function(index, user) {
	 $("#spisak").append(`<li>`+user.name+` `+user.surname+` - `+user.points+` points</li>`);	 
	 });
}

function logout(){
	$.ajax({
		 url: logout_url,
		 method: "GET",
		 success: function(){
			 sessionStorage.removeItem('loggedUser');
		 }
	});
}