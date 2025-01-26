package com.example.demo.application.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.example.demo.application.controllers")
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		// Map para armazenar os campos com erros e as mensagens correspondentes
		Map<String, String> validationErrors = new HashMap<>();

		// Itera sobre os erros de validação
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		// Retorna uma resposta com os erros de validação
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
		errorResponse.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResponse.put("message", "Erro de validação nos campos fornecidos.");
		errorResponse.put("errors", validationErrors);
		errorResponse.put("timestamp", System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralException(Exception ex) {
		return buildErrorResponse("Ocorreu um erro interno. Por favor, tente novamente mais tarde.",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", status.value());
		errorResponse.put("error", status.getReasonPhrase());
		errorResponse.put("message", message);
		errorResponse.put("timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, status);
	}
}
