package model;

import java.io.Serializable;
import java.util.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password_hash")
	private byte[] passwordHash;
	
	@Column(name = "salt")
	private byte[] salt;
	
	@Column(name = "role")
	private String role;
	
	
	
	
	public User(int userId, String username, byte[] password, byte[] salt, String role) {
		this.userId = userId;
		this.username = username;
		this.passwordHash = password;
		this.salt = salt;
		this.role = role;
	}
	
	public User(String username, byte[] passwordHash, byte[] salt, String role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.salt = salt;
		this.role = role;
	}
	
	public User() {
		
	}
	
	public int getId() {
		return this.userId;
		}
	
	public void setId(int id) {
		this.userId  = id;
		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public byte[] getPassword() {
		return this.passwordHash;
	}
	
	public void setPassword(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public byte[] getSalt() {
		return this.salt;
	}
	
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String toString() {
		return ""+Base64.getEncoder().encodeToString(this.passwordHash);
		
	}
}
