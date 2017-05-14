package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	public Optional<UserAccount> findByUserNameEquals(String userName);
}
