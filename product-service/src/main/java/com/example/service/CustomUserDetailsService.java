package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.UserRole;
import com.example.repository.UserAccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

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
				.orElseThrow(() -> new UsernameNotFoundException("can't find user " + username));
	}
}
