package com.api.v1.consultacep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EnderecoNotFoundException extends RuntimeException{
    String message = "Usuario não encontrado";

    public EnderecoNotFoundException(String cep){
        this.message = "Endereco com Cep" +cep+ "não encontrado";
    }

    public EnderecoNotFoundException(){

    }


}
