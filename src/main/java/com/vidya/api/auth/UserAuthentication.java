package com.vidya.api.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.vidya.api.db.models.Role;
import com.vidya.api.db.models.User;

public class UserAuthentication implements Authentication
{

	private static final long serialVersionUID = -8090800263955321111L;

	private final User user;
	private boolean authenticated = true;

	public UserAuthentication(User user)
	{
		this.user = user;
	}

	@Override
	public String getName()
	{
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return getGrantedAuthorities(getPrivileges(user.getRoles()));
	}

	private List<String> getPrivileges(Collection<Role> roles)
	{
		List<String> privileges = new ArrayList<String>();
		for (Role role : roles)
		{
			privileges.add(role.getCode());
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

	@Override
	public Object getCredentials()
	{
		return user.getPassword();
	}

	@Override
	public User getDetails()
	{
		return user;
	}

	@Override
	public Object getPrincipal()
	{
		return user.getUsername();
	}

	@Override
	public boolean isAuthenticated()
	{
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated)
	{
		this.authenticated = authenticated;
	}
}
