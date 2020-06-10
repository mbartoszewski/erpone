package com.bartoszewski.erpone.Repository;

import com.bartoszewski.erpone.Entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long>
{
	User findByEmail(String email);
}