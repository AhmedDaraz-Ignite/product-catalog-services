package com.example.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserAccount SpringData Jpa repository.
 * @author Ahmed.Rabie
 *
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	public Optional<UserAccount> findByUserNameEquals(String userName);
}
