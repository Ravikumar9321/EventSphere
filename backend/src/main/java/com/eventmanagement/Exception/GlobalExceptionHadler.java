package com.eventmanagement.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eventmanagement.Dto.ResponseStructure;


@ControllerAdvice
public class GlobalExceptionHadler extends ResponseEntityExceptionHandler  {
	

	// 🔹 Handle validation errors (from @Valid)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex,
	        HttpHeaders headers,
	        HttpStatusCode status,
	        WebRequest request) {

	    Map<String, String> fieldErrors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error ->
	        fieldErrors.put(error.getField(), error.getDefaultMessage())
	    );

	    ResponseStructure<Map<String, String>> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.BAD_REQUEST.value());
	    response.setMessage("Validation failed. Please correct the highlighted fields.");
	    response.setData(fieldErrors);

	    return ResponseEntity.badRequest().body(response);
	}

	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException e) {
		ResponseStructure<String> b=new ResponseStructure<>();
		b.setStatusCode(HttpStatus.NOT_FOUND.value());
		b.setMessage("Failure");
		b.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(b,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNRFE(NoRecordFoundException e) {
		ResponseStructure<String> b=new ResponseStructure<>();
		b.setStatusCode(HttpStatus.NOT_FOUND.value());
		b.setMessage("Failure");
		b.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(b,HttpStatus.NOT_FOUND);
	}

}
 