package com.vidya.api.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidya.api.models.Role;
import com.vidya.api.models.User;
import com.vidya.api.repository.UserRepository;

@Order(1)
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter 
{
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Autowired
	private UserRepository repository;

//	@Autowired
//	private AuthenticationManager authManager;
	
	public StatelessLoginFilter(AuthenticationManager authManager) 
	{
		super(new AntPathRequestMatcher("/login"));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException 
	{
		final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		//User authenticatedUser = repository.findUserByUsername(user.getUsername());
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		return getAuthenticationManager().authenticate(loginToken);
	}

	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) throws IOException, ServletException 
	{

		// Lookup the complete User object from the database and create an Authentication for it
		final User authenticatedUser = repository.findUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}
	
	 @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        if (request.getMethod().equals("POST")) {
            // If the incoming request is a POST, then we send it up
            // to the AbstractAuthenticationProcessingFilter.
            super.doFilter(request, response, chain);
        } else {
            // If it's a GET, we ignore this request and send it
            // to the next filter in the chain.  In this case, that
            // pretty much means the request will hit the /login
            // controller which will process the request to show the
            // login page.
            chain.doFilter(request, response);
        }
    }
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
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
			if(StringUtils.isNotBlank(privilege))
			{
				authorities.add(new SimpleGrantedAuthority(privilege));
			}
		}
		return authorities;
	}
}