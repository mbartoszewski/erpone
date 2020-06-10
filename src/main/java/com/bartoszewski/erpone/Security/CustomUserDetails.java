package com.bartoszewski.erpone.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bartoszewski.erpone.Entity.Role;
import com.bartoszewski.erpone.Entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -7524090310630621869L;
	Collection<Role> roles;
	User user;

	public CustomUserDetails(User user, Collection<Role> roles)
	{
		this.user = user;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : this.roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			role.getPrivileges().stream().map(p -> new SimpleGrantedAuthority(p.getName())).forEach(authorities::add);
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
}