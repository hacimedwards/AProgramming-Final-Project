package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	// ── Status constants ──────────────────────────────────────────────────────
	public static final String STATUS_PENDING   = "PENDING";
	public static final String STATUS_APPROVED  = "APPROVED";
	public static final String STATUS_REJECTED  = "REJECTED";
	public static final String STATUS_CANCELLED = "CANCELLED";

	// ── Equipment type constants ──────────────────────────────────────────────
	public static final String EQUIP_PC           = "PC";
	public static final String EQUIP_OSCILLOSCOPE = "Oscilloscope";
	public static final String EQUIP_SWITCH       = "Switch";
	public static final String EQUIP_3D_PRINTER   = "3D Printer";

	/** All equipment types available for reservation — used to populate UI combos. */
	public static final String[] EQUIPMENT_TYPES = {
			EQUIP_PC, EQUIP_OSCILLOSCOPE, EQUIP_SWITCH, EQUIP_3D_PRINTER
	};

	/**
	 * Returns how many units of a given equipment type exist per lab.
	 * Adjust these numbers to match your actual lab inventory.
	 */
	public static int getUnitCount(String equipmentType) {
		return switch (equipmentType) {
			case EQUIP_PC           -> 20;
			case EQUIP_OSCILLOSCOPE -> 8;
			case EQUIP_SWITCH       -> 6;
			case EQUIP_3D_PRINTER   -> 4;
			default                 -> 10;
		};
	}

	// ── Hibernate-mapped fields ───────────────────────────────────────────────

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private int reservationId;

	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "lab_id", nullable = false)
	private int labId;

	@Column(name = "seat_id", nullable = false)
	private int seatId;

	@Column(name = "equipment_id", nullable = false)
	private int equipmentId;

	@Column(name = "start_time", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime;

	/** One of: PENDING, APPROVED, REJECTED, CANCELLED */
	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	// ── Constructors ──────────────────────────────────────────────────────────

	public Reservation() {}

	public Reservation(int userId, int labId, int seatId, int equipmentId,
					   LocalDateTime startTime, LocalDateTime endTime) {
		this.userId        = userId;
		this.labId         = labId;
		this.seatId        = seatId;
		this.equipmentId   = equipmentId;
		this.startTime     = startTime;
		this.endTime       = endTime;
		this.status          = STATUS_PENDING;
	}

	// ── Getters & Setters ─────────────────────────────────────────────────────

	public int getReservationId()                       { return reservationId; }
	public void setReservationId(int reservationId)     { this.reservationId = reservationId; }

	public int getUserId()                              { return userId; }
	public void setUserId(int userId)                   { this.userId = userId; }

	public int getLabId()                               { return labId; }
	public void setLabId(int labId)                     { this.labId = labId; }

	public int getSeatId()                              { return seatId; }
	public void setSeatId(int seatId)                   { this.seatId = seatId; }

	public int getEquipmentId()                         { return equipmentId; }
	public void setEquipmentId(int equipmentId)         { this.equipmentId = equipmentId; }

	public LocalDateTime getStartTime()                 { return startTime; }
	public void setStartTime(LocalDateTime startTime)   { this.startTime = startTime; }

	public LocalDateTime getEndTime()                   { return endTime; }
	public void setEndTime(LocalDateTime endTime)       { this.endTime = endTime; }

	public String getStatus()                           { return status; }
	public void setStatus(String status)                { this.status = status; }

	public LocalDateTime getCreatedAt()                 { return createdAt; }
	public void setCreatedAt(LocalDateTime createdAt)   { this.createdAt = createdAt; }

	public LocalDateTime getUpdatedAt()                 { return updatedAt; }
	public void setUpdatedAt(LocalDateTime updatedAt)   { this.updatedAt = updatedAt; }

	@PrePersist
	private void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		createdAt = now;
		updatedAt = now;
	}

	@PreUpdate
	private void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return String.format(
				"Reservation[id=%d, userId=%d, labId=%d, seatId=%d, equipmentId=%d, start=%s, end=%s, status=%s, createdAt=%s, updatedAt=%s]",
				reservationId, userId, labId, seatId, equipmentId,
				startTime, endTime, status, createdAt, updatedAt);
	}
}