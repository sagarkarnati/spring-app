package com.vidya.utils;

import org.springframework.data.domain.AuditorAware;

public class MyAppAuditor implements AuditorAware<String>
{
    @Override
    public String getCurrentAuditor()
    {
	return "Vidya Sagar";
    }
}
