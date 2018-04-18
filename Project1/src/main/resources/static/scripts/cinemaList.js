var cinemas_url = "../api/culturalVenues/getCinemas"

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
                              <td><span class="font-weight-bold">`+data[i].averageGrade+`</span></td>
                              
                          </tr>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting cinemas!");
		 }
	});
}