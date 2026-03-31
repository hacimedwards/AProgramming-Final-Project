package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@Table(name = "lab_seat")
public class LabSeat {
	
	@Id
	@Column(name = "seat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;
	
	@Column(name = "seat_number")
	private int seatNumber;
	
	public LabSeat(int seatId, int seatNumber) {
		this.seatId = seatId;
		this.seatNumber = seatNumber;
	}
	
	public int getSeatId() {
		return this.seatId;
	}
	
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	
	public int getSeatNumber() {
		return this.seatNumber;
	}
	
	public void setSeatnumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
}
