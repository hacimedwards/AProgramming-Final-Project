package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "lab")
public class Lab {
	@Id
	@Column(name = "lab_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int labId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "school")
	private String school;
	
	@Column(name = "campus")
	private String campus;
	
	@Column(name = "seat_count")
	private int seatCount;
	
	public Lab(int labId, String name, String school, String campus, int seatCount) {
		this.labId = labId;
		this.name = name;
		this.school = school;
		this.campus = campus;
		this.seatCount = seatCount;
	}
	
	public Lab() {
		
	}
	
	public int getLabId() {
		return this.labId;
	}
	
	public void setLabId(int labId) {
		this.labId = labId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSchool() {
		return this.school;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getCampus() {
		return this.campus;
	}
	
	public void setCampus(String campus) {
		this.campus = campus;
		
	}
	
	public int getSeatCount() {
		return this.seatCount;
	}
	
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
}
