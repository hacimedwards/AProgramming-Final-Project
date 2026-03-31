package model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "reservation")
public class ReservationStatus {
	
	@Id
	@Column(name = "reservasion_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservasionId;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "status")
	private String status;
	
	public ReservationStatus(int reservasionId, String startTime, String endTime, String status){
		this.reservasionId = reservasionId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}
	public int getReservationId() {
		return this.reservasionId;
	}
	
	public void setReservationId(int reservationId) {
		this.reservasionId = reservationId;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
