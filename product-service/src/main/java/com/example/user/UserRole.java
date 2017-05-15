package com.example.user;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.example.common.model.BaseEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userCache")
@Cacheable
@Entity(name = "USER_ROLE")
public class UserRole extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private UserAccount user;

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}
}
