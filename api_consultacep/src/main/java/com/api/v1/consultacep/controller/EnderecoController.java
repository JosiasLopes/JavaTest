package com.api.v1.consultacep.controller;

import com.api.v1.consultacep.entity.Endereco;
import com.api.v1.consultacep.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value ="api/v1/endereco")
@Api(value="Api Rest calcular frete")
public class EnderecoController {


    @Autowired
    EnderecoService enderecoService;

    @ApiOperation(value="Retorna um endereço através do cep passado na url")
    @GetMapping(value="/getCep/{cep}")
    public ResponseEntity<Endereco> buscaCep(@PathVariable(name = "cep") String cep) {
        return this.enderecoService.getEnderecoByCep(cep);

    }

}
