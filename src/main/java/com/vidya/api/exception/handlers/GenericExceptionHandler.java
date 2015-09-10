package com.vidya.api.exception.handlers;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GenericExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GenericExceptionHandler.class);

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public void handleException(HttpServletRequest req, Exception ex) {
		LOG.error(ex.getMessage(), ex);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ IllegalArgumentException.class, org.springframework.validation.BindException.class })
	@ResponseBody
	public void handleBadRequest(HttpServletRequest req, Exception ex) {
		LOG.error(ex.getMessage(), ex);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ AccessDeniedException.class })
	@ResponseBody
	public void handleAccessDeniedRequest(HttpServletRequest req, Exception ex) {
		LOG.error(ex.getMessage(), ex);
	}
}