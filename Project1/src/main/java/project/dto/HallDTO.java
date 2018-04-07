package project.dto;

import project.domain.Hall;

public class HallDTO {
	
	private Long id;
	private int hallId;
	private int rows;
	private int seatsPerRow;
	private CulturalVenueDTO culturalVenue;
	
	public HallDTO(Hall hall){
		this.id = hall.getId();
		this.hallId = hall.getHallId();
		this.rows=hall.getRows();
		this.seatsPerRow=hall.getSeatsPerRow();
		this.culturalVenue = new CulturalVenueDTO(hall.getCulturalVenue());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}

	public CulturalVenueDTO getCulturalVenue() {
		return culturalVenue;
	}

	public void setCulturalVenue(CulturalVenueDTO culturalVenue) {
		this.culturalVenue = culturalVenue;
	}
}
