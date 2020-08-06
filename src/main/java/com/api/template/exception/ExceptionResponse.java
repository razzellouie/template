package com.api.template.exception;

import org.springframework.stereotype.Component;

import lombok.Data; 

@Data
@Component
public class ExceptionResponse {

    private String message;
	private int code;
	
}
