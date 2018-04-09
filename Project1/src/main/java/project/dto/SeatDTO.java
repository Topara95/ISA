package project.dto;

import project.domain.Seat;

public class SeatDTO {
	
	private Long id;
	private int row;
	private int seatInRow;
	private HallDTO hall;
	
	public SeatDTO(Seat seat){
		this.id = seat.getId();
		this.row = seat.getRow();
		this.seatInRow = seat.getSeatInRow();
		this.hall = new HallDTO(seat.getHall());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getSeatInRow() {
		return seatInRow;
	}

	public void setSeatInRow(int seatInRow) {
		this.seatInRow = seatInRow;
	}

	public HallDTO getHall() {
		return hall;
	}

	public void setHall(HallDTO hall) {
		this.hall = hall;
	}
}
