package com.vidya.api.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vidya.api.db.models.User;

@Service
public class TokenAuthenticationService 
{
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

	private static final String SECRET = "9SyECk96oDsTmXfogIieDI0cD/8FpnojlYSUJT5U9I/FGVmBz5oskmjOR8cbXTvoPjX+Pq/T/b1PqpHX0lYm0oCBjXWICA==";
	
	private final TokenHandler tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(SECRET));

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) 
	{
		final User user = authentication.getDetails();
		user.setExpires(System.currentTimeMillis() + TEN_DAYS);
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) 
	{
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) 
		{
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) 
			{
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
