package com.qualfacul.hades.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "This operation can only be used by administrators")
public class OnlyAdminsCanAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
