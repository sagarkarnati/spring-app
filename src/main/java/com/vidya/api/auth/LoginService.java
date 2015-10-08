package com.vidya.api.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vidya.api.db.models.Role;
import com.vidya.api.db.models.User;
import com.vidya.api.repository.UserRepository;

@Service("loginService")
public class LoginService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		List<User> findAll = userRepository.findAll();
<<<<<<< HEAD
		com.vidya.api.models.User user = null;
		for (User usr : findAll)
=======
		com.vidya.api.db.models.User user = null;
		for(User usr : findAll)
>>>>>>> branch 'master' of https://github.com/sagarkarnati/spring-app
		{
			if (usr.getUsername().equals(username))
				user = usr;
		}
		if (user == null)
		{
			throw new UsernameNotFoundException("username " + username + " not available");
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true,
				getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles)
	{
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles)
	{
		List<String> privileges = new ArrayList<String>();
		for (Role role : roles)
		{
			privileges.add(role.getName());
			privileges.addAll(role.getPrivileges());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges)
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String privilege : privileges)
		{
			if (StringUtils.isNotBlank(privilege))
			{
				authorities.add(new SimpleGrantedAuthority(privilege));
			}
		}
		return authorities;
	}
}