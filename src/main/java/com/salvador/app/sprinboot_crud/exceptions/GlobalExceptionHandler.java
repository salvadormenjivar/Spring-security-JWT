package com.salvador.app.sprinboot_crud.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleUniqueConstraintViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body("El usuario ya existe en la base de datos.");
    }
}
