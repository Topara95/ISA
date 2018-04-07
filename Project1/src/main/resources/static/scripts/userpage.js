var loggeduser = JSON.parse(sessionStorage.getItem('loggedUser'));
var edit_url="../api/users/"+loggeduser.id
var requests_url = "../api/users/getRequests/"+loggeduser.id
var friends_url = "../api/users/getFriends/"+loggeduser.id
var theaters_url = "../api/culturalVenues/getTheaters"
var cinemas_url = "../api/culturalVenues/getCinemas"

$(document).on('submit','.editform', function(e) {
	e.preventDefault();
	var p = $('#password').val();
	var cp = $('#password-confirm').val();
	if(p == cp){
		$.ajax({
			type : 'PUT',
			url : edit_url,
			contentType : 'application/json',
			dataType : "json",
			data:formToJSON(),
			success : function(data) {
				location.reload();
				sessionStorage.setItem('loggedUser',JSON.stringify(data));
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("AJAX ERROR: " + errorThrown+"signin");
			}
		});
	}else{
		alert("Passwords must match");
	}
});


function formToJSON() {
	return JSON.stringify({
	"id":loggeduser.id,
	"email":loggeduser.email,
    "name":$('#name').val(),
    "surname":$('#surname').val(),
    "city":$('#city').val(),
    "phone":$('#phone').val(),
    "password":$('#password').val()
	});
}

function generateUserInfo(){
	document.getElementById("name").value = loggeduser.name;
	document.getElementById("surname").value = loggeduser.surname;
	document.getElementById("city").value = loggeduser.city;
	document.getElementById("phone").value = loggeduser.phone;
	document.getElementById("password").value = loggeduser.password;
	document.getElementById("password-confirm").value = loggeduser.password;
}

function getFriendRequests(){
	$.ajax({
		 url: requests_url,
		 method: "GET",
		 success: function(data){
			 $(".freqtable").empty();
			 for(i=0;i<data.length;i++){
				 $(".freqtable").append(`<tr>
                                <td>
                                   <span class="pull-xs-right font-weight-bold">`+data[i].name+` `+data[i].surname+`</span> sent you a friend request
                                   <span class="float-right"><button type="button" name=`+data[i].id+` class="btn btn-success btn-sm accreq">Accept</button>
                                   <button type="button" name=`+data[i].id+` class="btn btn-danger btn-sm decreq">Decline</button></span>
                                </td>
                            </tr>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting friend requests!");
		 }
	});
}

function getFriends(){
	$.ajax({
		 url: friends_url,
		 method: "GET",
		 success: function(data){
			 $(".friendsTable").empty();
			 for(i=0;i<data.length;i++){
				 $(".friendsTable").append(`<tr>
                               <td>
                                  <span class="font-weight-bold">`+data[i].name+`  `+data[i].surname+` | `+data[i].email+` | `+data[i].city+` | `+data[i].phone+`</span>
                                  <span class="float-right"><a href="#"><img onclick="removeFriend(`+data[i].id+`)" src="images/remove.png" title="remove from friends"></img></a></span>
                               </td>
                           </tr>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting Friends!");
		 }
	});
}

$(document).on('click','.accreq', function(e) {
	e.preventDefault();
	var id = $(this).attr('name');
	$.ajax({
		 url: "../api/users/approveFriendRequest/"+id,
		 method: "GET",
		 success: function(){
			 
				 getFriendRequests();
				 getFriends();
				 toastr.success("You have accepted a friend request!")
		 },
		 error: function(){
			 alert("Error approving request");
		 }
	});
});

$(document).on('click','.decreq', function(e) {
	e.preventDefault();
	//alert($(this).attr('name'));
	var id = $(this).attr('name');
	$.ajax({
		 url: "../api/users/declineRequest/"+id,
		 method: "GET",
		 success: function(){
			 
				 getFriendRequests();
				 toastr.info('You have declined a friend request!');
				 
			 
		 },
		 error: function(){
			 alert("Error approving request");
		 }
	});
});

$(document).on('click','#searchPeople',function(e){
	e.preventDefault();
	var name = $('#searchname').val();
	var surname = $('#searchsurname').val();
	if(name == ''){
		name = "nema";
	}
	if(surname ==''){
		surname="nema";
	}
	
	$.ajax({
		 url: "../api/users/search/"+name+"/"+surname,
		 method: "GET",
		 success: function(data){
			 $(".searchedpeople").empty();
			 for(i=0;i<data.length;i++){
				 $(".searchedpeople").append(`<tr>
                         <td>
                            <span class="font-weight-bold">`+data[i].name+`  `+data[i].surname+` | `+data[i].email+` | `+data[i].city+` | `+data[i].phone+`</span>
                            <span class="float-right"><a href="#"><img src="images/sendRequest.png" onclick="sendFriendRequest(`+data[i].id+`)" title="send friend request"></img></a></span>
                         </td>
                     </tr>`);
			 }
				 
		 },
		 error: function(){
			 alert("Error searching people");
		 }
	});
});

function sendFriendRequest(receiverid){
	$.ajax({
		 url: "../api/users/sendFriendRequest/"+receiverid,
		 method: "GET",
		 success: function(){
			 toastr.success("Friend request sent!");
				 
		 },
		 error: function(){
			 toastr.error("Sending request failed. You have already sent one!");
		 }
	});
}

function removeFriend(removingId){
	$.ajax({
		 url: "../api/users/removeFriend/"+removingId,
		 method: "GET",
		 success: function(){
			 toastr.info("Friend removed!");
			 getFriends();
		 },
		 error: function(){
			 toastr.error("Error removing friend");
		 }
	});
}

function getTheaters(){
	$.ajax({
		 url: theaters_url,
		 method: "GET",
		 success: function(data){
			 $(".theatersTable").empty();
			 for(i=0;i<data.length;i++){
				 $(".theatersTable").append(`<tr>
                               <td><span class="font-weight-bold">`+data[i].name+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].address+`</span></td>
                              <td><span class="font-weight-bold">`+data[i].description+`</span></td>
                              <td><button onclick="generateRepertoire(`+data[i].id+`)" id=`+data[i].id+` type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#exampleModalR">Look</button></td>
                           </tr>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting theaters!");
		 }
	});
}

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
                              <td><button onclick="generateRepertoire(`+data[i].id+`)" id=`+data[i].id+` type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#exampleModalR">Look</button></td>
                          </tr>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting cinemas!");
		 }
	});
}

function generateRepertoire(id){
	$.ajax({
		 url: "../api/culturalVenues/"+id+"/getEvents",
		 method: "GET",
		 success: function(data){
			 $("#events").empty();
			 for(i=0;i<data.length;i++){
				 $("#events").append(`<option id=`+data[i].id+`>`+data[i].name+`</option>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting repertoire!");
		 }
	});
	
}

$(document).on('click','#genProjectionDates',function(e){
	e.preventDefault();
	var id = $('#events option:selected').attr('id')
	$.ajax({
		 url: "../api/events/"+id+"/eventProjections",
		 method: "GET",
		 success: function(data){
			 $("#projectiondiv").empty();
			 if(data.length != 0){
				 $("#projectiondiv").append(`<label for="projectiondates">Date: </label>`);
				 $("#projectiondiv").append(`<select id="projectiondates">
	                              	</select>
	                              	<button type="button" class="btn btn-info btn-sm" id="genProjectionTimes">Continue</button>`);
			 }
			 for(i=0;i<data.length;i++){
				 $("#projectiondates").append(`<option id=`+data[i].id+`>`+data[i].projectionDate+`</option>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting projections!");
		 }
	});
});