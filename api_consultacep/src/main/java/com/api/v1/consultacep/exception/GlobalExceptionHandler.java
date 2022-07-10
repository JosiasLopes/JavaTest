package com.api.v1.consultacep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler{

    //se o metodo retornar uma exception o handler vai tratar de acordo
    @ExceptionHandler(EnderecoNotFoundException.class)
    public ResponseEntity<?> EnderecoNotFoundException(EnderecoNotFoundException ex, WebRequest request){
        ErrorDatails detalhes = new ErrorDatails(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalhes,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConsultaFreteNotFoundException.class)
    public ResponseEntity<?> ConsultaFreteNotFoundException(ConsultaFreteNotFoundException ex, WebRequest request){
        ErrorDatails detalhes = new ErrorDatails(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalhes,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request){
        ErrorDatails detalhes = new ErrorDatails(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detalhes,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
