package com.ejada.task2wallet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejada.task2wallet.dto.CreateUserDto;
import com.ejada.task2wallet.dto.LoginUserDto;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/registration")
	public User userRegistration(@RequestBody CreateUserDto user) {
		return userService.userRegistration(user);
	}
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginUserDto user){
		return userService.userLogin(user);
	}
	
}
