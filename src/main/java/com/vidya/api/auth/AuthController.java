package com.vidya.api.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
	@RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
	public void login() 
	{
		System.out.println("Inside options");
	}
	
	
}
