package com.example.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userCache")
@Cacheable
@Entity(name = "USER")
public class UserAccount extends BaseEntity {

	@Column(name = "USERNAME", length = 128, nullable = false)
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name="ACTIVE", columnDefinition="tinyint(1) default 1")
	private boolean active;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserRole> roles;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
	
	
}
