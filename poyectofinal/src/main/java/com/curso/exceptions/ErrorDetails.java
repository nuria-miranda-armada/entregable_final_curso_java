package com.curso.exceptions;



import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDetails{
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	public ErrorDetails(LocalDateTime timestamp,String message,String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		
	}
}
