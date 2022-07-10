package com.api.v1.consultacep.service;


import com.api.v1.consultacep.entity.Endereco;
import com.api.v1.consultacep.exception.EnderecoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EnderecoService {

    @Autowired
    private
    RestTemplate restTemplate;

    public ResponseEntity<Endereco> getEnderecoByCep(String cep){
        String uri = "http://viacep.com.br/ws/{cep}/json/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("cep", cep);
        Endereco endereco = restTemplate.getForObject(uri, Endereco.class, params);

        if(endereco!=null){
            Endereco tmp = endereco;
            return ResponseEntity.ok().body(tmp);
        }else{
            throw new EnderecoNotFoundException(cep);
        }

    }
}
