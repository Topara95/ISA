var islogged_url = "http://localhost:8080/api/users/isLoggedIn"

function generateNavbar(){
	 $.ajax({
		 url: islogged_url,
		 method: "GET",
		 success: function(data){
			 var user = data;
			 if(user.email!=null){
				 console.log("aaaaaaa"+user.email);
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
	                <a class="nav-link" href="#">Services</a>
	              </li>
	              <li class="nav-item">
	                <a class="nav-link" href="#">User `+user.email+` signed in</a>
	              </li>`);
			 }else{
				 console.log("BEEEEEEEEE"+user.email);
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
			                <a class="nav-link" href="#">Register</a>
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