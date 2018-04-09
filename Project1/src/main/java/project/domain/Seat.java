package project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Seat implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private int row;
	
	@Column(nullable = false)
	private int seatInRow;
	
	@ManyToOne
	private Hall hall;
	
	public Seat(){}
	
	public Seat(int row, int seatInRow, Hall hall){
		this.row = row;
		this.seatInRow = seatInRow;
		this.hall = hall;
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

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}
}
