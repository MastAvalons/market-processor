package com.poc.marketprocessor.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.poc.marketprocessor.model.ResponseMessage;

/**
 * Called when an exception occurs during request processing. Transforms
 * exception message into JSON format.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleGenericException(Exception ex,
			WebRequest request) {
		ex.printStackTrace();
		return new ResponseEntity<>(new ResponseMessage(
				ResponseMessage.Type.danger, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseMessage processValidationError(
			MethodArgumentNotValidException ex) { 
		BindingResult result = ex.getBindingResult();
		
		StringBuilder message = new StringBuilder();
		
		for (FieldError error : result.getFieldErrors()) {
			message.append(error.getDefaultMessage() + ", ");
		}
		
		message.deleteCharAt(message.lastIndexOf(","));
		
		return ResponseMessage.warning(message.toString());
	}

}
