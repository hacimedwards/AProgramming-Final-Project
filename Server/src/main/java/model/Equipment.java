package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
@Table(name = "equipment")
public class Equipment {
	
	@Id
	@Column(name = "equipment_id")
	private String equipmentId;
	@Column(name ="type")
	private String type;
	
	@Column(name = "status")
	private String status;

	@Column(name = "lab_id")
	private Integer labId;
	
	public Equipment(String equipmentId, String type, String status, Integer labId) {
		this.equipmentId = equipmentId;
		this.type = type;
		this.status = status;
		this.labId = labId;
	}
	
	public Equipment() {
		
	}
	
	public String getEquipmentId() {
		return this.equipmentId;
	}
	
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	// name column removed from DB; use `type` as the identifying label
	
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

	public Integer getLabId() {
		return labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
	}
}
