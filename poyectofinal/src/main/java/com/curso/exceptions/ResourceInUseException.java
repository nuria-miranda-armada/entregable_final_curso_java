package com.curso.exceptions;

public class ResourceInUseException extends RuntimeException {
	
 public ResourceInUseException(String errorMessage, Throwable err) {
	        super(errorMessage, err);
	    }
 
 public ResourceInUseException(String errorMessage) {
     super(errorMessage);
 }


}
