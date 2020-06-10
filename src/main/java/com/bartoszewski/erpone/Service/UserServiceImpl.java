package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.User;
import com.bartoszewski.erpone.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService
{
	UserRepository userRepository;

	@Autowired
	UserServiceImpl(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<User> create(User entity, Authentication authentication) throws ResponseStatusException
	{
		if (emailExist(entity.getEmail()))
		{
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
			    "There is an account with that email address: " + entity.getEmail());
		}
		else
		{
			User user = entity;
			System.out.println(user.getPassword() + " " + user.getEmail());
			user.setPassword(String.valueOf(BcryptEncoder(user.getPassword())));
			return new ResponseEntity<>(userRepository.save(entity), HttpStatus.CREATED);
		}
	}

	@Override
	public ResponseEntity<Page<User>> readAll(Pageable pageable)
	{
		return new ResponseEntity<Page<User>>(userRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> readById(Long id)
	{
		return new ResponseEntity<>(
		    userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> updateById(Long id, User entity)
	{
		return null;
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (userRepository.getOne(id) != null)
		{
			userRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

	private boolean emailExist(String email)
	{
		return userRepository.findByEmail(email) != null;
	}

	public CharSequence BcryptEncoder(CharSequence rawPassword)
	{
		return new BCryptPasswordEncoder().encode(rawPassword);
	}
}