package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.User;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Long>
{
	UserService userService;

	@Autowired
	public UserController(BaseService<User, Long> service, UserService userService)
	{
		super(service);
		this.userService = userService;
	}
}