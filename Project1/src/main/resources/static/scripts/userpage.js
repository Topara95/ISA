var loggeduser = JSON.parse(sessionStorage.getItem('loggedUser'));
var edit_url="../api/users/"+loggeduser.id
var requests_url = "../api/users/getRequests/"+loggeduser.id
var friends_url = "../api/users/getFriends/"+loggeduser.id

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
			 for(i=0;i<data.length;i++){
				 $(".friendsTable").append(`<tr>
                               <td>
                                  <span class="font-weight-bold">`+data[i].name+`  `+data[i].surname+` | `+data[i].email+` | `+data[i].city+` | `+data[i].phone+`</span>
                                  <span class="float-right"></span>
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
	//alert($(this).attr('name'));
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