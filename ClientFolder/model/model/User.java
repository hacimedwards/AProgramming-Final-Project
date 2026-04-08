package model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String username;
    private byte[] passwordHash;
    private byte[] salt;
    private String role;

    public User(int userId, String username, byte[] passwordHash, byte[] salt, String role) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.role = role;
    }

    public User(String username, byte[] passwordHash, byte[] salt, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.role = role;
    }

    public int getId() {
        return this.userId;
    }

    public void setId(int id) {
        this.userId = id;
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
}

