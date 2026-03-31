package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "equipment")
public class Equipment {
	
	@Id
	@Column(name = "equipment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int equipmentId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name ="type")
	private String type;
	
	@Column(name = "status")
	private String status;
	
	public Equipment(int equipmentId, String name, String type, String status) {
		this.equipmentId = equipmentId;
		this.name = name;
		this.type = type;
		this.status = status;
	}
	
	public Equipment() {
		
	}
	
	public int getEquipmentId() {
		return this.equipmentId;
	}
	
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
