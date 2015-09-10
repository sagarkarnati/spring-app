package com.vidya.api.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vidya.api.models.User;
import com.vidya.api.repository.UserRepository;

@RestController(value="abc")
public class UserController
{
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
	public User getCurrent()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UserAuthentication)
		{
			return ((UserAuthentication) authentication).getDetails();
		}
		return new User("vidya"); // anonymous user support
	}

	@RequestMapping(value = "/admin/api/users", method = RequestMethod.GET)
	public List<User> list()
	{
		return userRepository.findAll();
	}
}
