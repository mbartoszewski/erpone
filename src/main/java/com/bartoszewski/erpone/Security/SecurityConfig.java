package com.bartoszewski.erpone.Security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hibernate.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService customUsersDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().cors().and().authorizeRequests(authorize -> authorize.antMatchers("/**").permitAll())
				.csrf().disable()

				.formLogin();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUsersDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		List<String> url = new ArrayList<>(Arrays.asList("*"));
		List<String> method = new ArrayList<>(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		List<String> header = new ArrayList<>(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		configuration.setAllowedOrigins(url); // www - obligatory
		// configuration.setAllowedOrigins(ImmutableList.of("*")); //set access from all
		// domains
		configuration.setAllowedMethods(method);
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(header);

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}