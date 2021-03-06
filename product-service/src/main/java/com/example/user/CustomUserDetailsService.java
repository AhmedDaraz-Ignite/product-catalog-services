package com.example.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation for spring security user details service.
 * @author Ahmed.Rabie
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final String CANNOT_FIND_USER = "can't find user ";
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userAccountRepository.findByUserNameEquals(username)
				.map(user -> {
					List<String> roles = user.getRoles().stream().map(UserRole::getName).collect(Collectors.toList());
					return new User(
							user.getUserName(), 
							user.getPassword(),
							AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()]))
					 );
				})
				.orElseThrow(() -> new UsernameNotFoundException(CANNOT_FIND_USER + username));
	}
}
