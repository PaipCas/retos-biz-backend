package com.cesde.retosbiz.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	// Categories
	@ExceptionHandler(CategoryAlreadyExistException.class)
	public ResponseEntity<Error> handleException(CategoryAlreadyExistException ex) {
		Error error = new Error("category-already-exist", ex.getMessage());

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Error> handleException(CategoryNotFoundException ex) {
		Error error = new Error("category-not-found", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// Priorities
	@ExceptionHandler(PriorityAlreadyExistException.class)
	public ResponseEntity<Error> handleException(PriorityAlreadyExistException ex) {
		Error error = new Error("priority-already-exist", ex.getMessage());

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(PriorityNotFoundException.class)
	public ResponseEntity<Error> handleException(PriorityNotFoundException ex) {
		Error error = new Error("priority-not-found", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// Companies
	@ExceptionHandler(CompanyAlreadyExistException.class)
	public ResponseEntity<Error> handleException(CompanyAlreadyExistException ex) {
		Error error = new Error("company-already-exist", ex.getMessage());

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<Error> handleException(CompanyNotFoundException ex) {
		Error error = new Error("company-not-found", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	// Generals
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
		List<Error> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.add(new Error(error.getField(), error.getDefaultMessage()));
		});

		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleException(Exception ex) {
		Error error = new Error("unknown-error", ex.getMessage());

		return ResponseEntity.internalServerError().body(error);
	}
}