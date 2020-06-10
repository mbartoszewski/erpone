package com.bartoszewski.erpone.Security;

import com.bartoszewski.erpone.Entity.User;
import com.bartoszewski.erpone.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService 
{
	UserRepository userRepository;
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user== null)
		{
			throw new UsernameNotFoundException("User with email: " + username + " not found.");
		}
		return new CustomUserDetails(user, user.getRoles());
	}
}