package com.api.v1.consultacep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ConsultaFreteNotFoundException extends RuntimeException{
    String message = "Usuario não encontrado";

    public ConsultaFreteNotFoundException(Long id){
        this.message = "Consulta não encontrada" +id+ "não encontrado";
    }

    public ConsultaFreteNotFoundException(){

    }


}
