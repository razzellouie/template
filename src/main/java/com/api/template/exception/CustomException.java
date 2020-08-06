package com.api.template.exception;

import org.springframework.stereotype.Component;

import lombok.Data; 


@Data
@Component
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String message;
    private int code;
    
    public CustomException(){}

    public CustomException(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}
    
    
}