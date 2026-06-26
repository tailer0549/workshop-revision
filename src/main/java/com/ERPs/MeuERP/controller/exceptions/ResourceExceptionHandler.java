package com.ERPs.MeuERP.controller.exceptions;

import com.ERPs.MeuERP.service.exceptions.DatabaseException;
import com.ERPs.MeuERP.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // Diz que é uma classe global de tratamento de exceções
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // Toda exceção que estiver dentro dessa anotation será interceptada
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; // Status code do nosso timestamp
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err); // .status() retorna o nosso código de status personalizado (404)
    }

    // Metodo para tratatar erro de DatabaseException
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        // Criando a mensagem de erro padrão para a nossa exceção DatabaseException
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
