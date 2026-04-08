package model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "reservation_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "lab_id")
	private Integer labId;

	@Column(name = "seat_id")
	private Integer seatId;

	@Column(name = "equipment_id")
	private String equipmentId;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@Column(name = "end_time")
	private LocalDateTime endTime;

	@Column(name = "status")
	private String status;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// JPA requires a no-arg constructor
	public Reservation() {
	}

	// Convenience constructor (id is usually generated)
	public Reservation(Integer userId, Integer labId, Integer seatId, String equipmentId,
					   LocalDateTime startTime, LocalDateTime endTime, String status,
					   LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.labId = labId;
		this.seatId = seatId;
		this.equipmentId = equipmentId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLabId() {
		return labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Reservation that = (Reservation) o;
		// If id is set, use it for equality; otherwise fall back to comparing fields
		if (reservationId != null && that.reservationId != null) {
			return Objects.equals(reservationId, that.reservationId);
		}
		return Objects.equals(userId, that.userId) &&
				Objects.equals(labId, that.labId) &&
				Objects.equals(seatId, that.seatId) &&
				Objects.equals(equipmentId, that.equipmentId) &&
				Objects.equals(startTime, that.startTime) &&
				Objects.equals(endTime, that.endTime) &&
				Objects.equals(status, that.status);
	}

	@Override
	public int hashCode() {
		if (reservationId != null) return Objects.hash(reservationId);
		return Objects.hash(userId, labId, seatId, equipmentId, startTime, endTime, status);
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"reservationId=" + reservationId +
				", userId=" + userId +
				", labId=" + labId +
				", seatId=" + seatId +
				", equipmentId=" + equipmentId +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", status='" + status + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
