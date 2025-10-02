package com.project.resources.exceptions;

import com.project.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// Classe global para tratamento de exceções em controllers
@ControllerAdvice
public class ResourceExceptionHandler {

    // Handler para quando objeto não é encontrado
    @ExceptionHandler
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Object Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Handler para erro de tipo de argumento (ex: string onde era número)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Bad Request", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Handler para erros de validação de campos (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        ValidationError errors = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Validation Error",
                "Field Validation Error",
                request.getRequestURI()
        );

        // Coleta todos os erros de campo da validação
        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            errors.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}