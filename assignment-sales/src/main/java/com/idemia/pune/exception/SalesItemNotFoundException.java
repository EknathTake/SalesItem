package com.idemia.pune.exception;

import java.time.LocalDateTime;

import javassist.NotFoundException;
import lombok.Data;

@Data
public class SalesItemNotFoundException extends NotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2071235530833472820L;

	private int status;
	
	private String message;
	
	private LocalDateTime timestamp  = LocalDateTime.now();
	
	public SalesItemNotFoundException(String message, int status) {
		super(message);
		this.message = message;
		this.status = status;
	}

	
}
