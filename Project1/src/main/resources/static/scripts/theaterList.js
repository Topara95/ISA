
var theaters_url = "../api/culturalVenues/getTheaters"

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
                               <td><span class="font-weight-bold">`+data[i].averageGrade+`</span></td>
                           </tr>`);
			 }
		 },
		 error: function(){
			 alert("Error while getting theaters!");
		 }
	});
}