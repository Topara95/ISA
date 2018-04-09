$(document).on('click','#genSeats',function(e){
	//
	var list=[];
	var eventId = $('#events option:selected').attr('id')
	var projectionId = $('#projectiondates option:selected').attr('id')
	var timeId = $('#projectiontimes option:selected').attr('id')
	//var hallId = $('#projectiontimes option:selected').attr('name')
	$.ajax({
		 url: "../api/events/"+eventId+"/eventProjections/"+projectionId+"/projectionTimes/"+timeId+"/seats",
		 method: "GET",
		 async : false,
		 success: function(data){
			 $("#seatsdiv").empty();
			 $("#seatsdiv").append(`<div id="seat-map">
									<div class="front">SCREEN</div>	
								</div>
									<button type="button" id="reserveProjection" class="btn btn-primary">Reserve</button>			
								`);
			 if(data.length != 0){
				 
			 }
			 
			var hallrows = data[1].hall.rows;
			var seatsperrow = data[1].hall.seatsPerRow;
			
			for(i=0;i<hallrows;i++){
				var row ='';
				for(j=0;j<seatsperrow;j++){
						row+='a';
				}
				list.push(row);
			}
			flag = true;
		 },
		 error: function(){
			 alert("Error while getting seats!");
		 }
	});
	//
	var sc = $('#seat-map').seatCharts({
		map:list,
		seats: {
			a: {
				price   : 99.99,
				classes : 'front-seat'
			}
		
		},
		click: function () {
			if (this.status() == 'available') {
				//do some stuff, i.e. add to the cart
				return 'selected';
			} else if (this.status() == 'selected') {
				//seat has been vacated
				return 'available';
			} else if (this.status() == 'unavailable') {
				//seat has been already booked
				return 'unavailable';
			} else {
				return this.style();
			}
		}
	});

	//Make all available 'c' seats unavailable
	sc.find('c.available').status('unavailable');
	
	/*
	Get seats with ids 2_6, 1_7 (more on ids later on),
	put them in a jQuery set and change some css
	*/
	/*sc.get(['2_6', '1_7']).node().css({
		color: '#ffcfcf'
	});*/
	
	console.log('Seat 1_2 costs ' + sc.get('1_2').data().price + ' and is currently ' + sc.status('1_2'));
});

$(document).on('click','#reserveProjection',function(e){
	var sc = $('#seat-map').seatCharts();
    sc.find('a.selected').status('unavailable');
    alert(sc.find('a.unavailable'));
    console.log(sc.find('a.unavailable').seatIds);
});