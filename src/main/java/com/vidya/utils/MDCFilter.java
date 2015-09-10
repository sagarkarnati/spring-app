package com.vidya.utils;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

public class MDCFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
	try
	{
	    UUID uuid = UUID.randomUUID();
	    MDC.put("requestId", uuid.toString());
	    chain.doFilter(request, response);
	}catch(Exception e)
	{
	    e.printStackTrace();
	}finally
	{
	    MDC.remove("requestId");
	}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }
}