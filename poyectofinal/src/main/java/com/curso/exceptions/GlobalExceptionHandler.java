package com.curso.exceptions;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// maneja/captura todas las excepciones de toda nuestra aplicación. 
@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
//Controla cuando se intenta borrar un recurso que está siendo referenciado en otra tabla
	//ej: Intento borrar un poder que tiene un superheroe
	@ExceptionHandler(ResourceInUseException.class)
	public ResponseEntity<?> resourceNotFoundEception(ResourceInUseException ex,
			WebRequest request){
		ErrorDetails errorDetails =  new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	//controla cuando no se encuentra un recurso
	//ej: busco un superheroe por id y no existe.
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundEception(ResourceNotFoundException ex,
			WebRequest request){
		ErrorDetails errorDetails =  new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	//Controla el rs¡
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler (Exception ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
