package com.syntaxterror.bestseller.model;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Username with unique constraint
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @ManyToOne
    private Role role;
    
    @Column(name = "tuomari_id", nullable = true)
    private Long tuomariId;

    public Account() {
    }

    public Account(String username, String passwordHash, Role role, Long tuomariId) {
        super();
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.tuomariId = tuomariId;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	public Long getTuomariId() {
		return tuomariId;
	}

	public void setTuomariId(Long tuomariId) {
		this.tuomariId = tuomariId;
	}

    
}
