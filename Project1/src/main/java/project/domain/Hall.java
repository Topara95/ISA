package project.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Hall implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private int rows;
	
	@Column(nullable = false)
	private int seatsPerRow;
	
	@OneToMany(targetEntity = Seat.class)
	private List<Seat> seats;
	
	@ManyToOne(targetEntity = CulturalVenue.class)
	private CulturalVenue culturalVenue;
	
	public Hall(){}
	
	public Hall(int rows, int seatsPerRow, CulturalVenue culturalVenue){
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
		this.culturalVenue = culturalVenue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public CulturalVenue getCulturalVenue() {
		return culturalVenue;
	}

	public void setCulturalVenue(CulturalVenue culturalVenue) {
		this.culturalVenue = culturalVenue;
	}

}
