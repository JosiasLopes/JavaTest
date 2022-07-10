package com.api.v1.consultacep.controller;

import com.api.v1.consultacep.entity.ConsultaFrete;
import com.api.v1.consultacep.entity.Endereco;
import com.api.v1.consultacep.exception.ConsultaFreteNotFoundException;
import com.api.v1.consultacep.service.ConsultaFreteService;
import com.api.v1.consultacep.service.EnderecoService;
import com.api.v1.consultacep.utils.calc.CalcFrete;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

//foi usado o crossorigin porque é a porta padrão do angular
@RestController @CrossOrigin(origins="*")
@RequestMapping(value ="api/v1/contacaofrete")
@Api(value="Api Rest calcular frete")
public class ConsultaFreteController {

    @Autowired
    private ConsultaFreteService ctService;

    @Autowired
    private EnderecoService edService;

    @ApiOperation(value="Retorna a lista com todos as consultas salvas")
    @GetMapping()
    public List<ConsultaFrete> getAllConsultaFrete(){
        return ctService.getAllConsultaFrete();
    }

    @ApiOperation(value="Retorna uma consulta de frete salva no banco através do id")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaFrete> getConsultaFreteById(@PathVariable(value="id") Long id){

        return ctService.getConsultaFreteById(id);

    }

    @ApiOperation(value="Salva uma consulta no banco passando os parâmetros através do body da requisição")
    @PostMapping
    public ConsultaFrete createConsultaFrete(@Valid @RequestBody ConsultaFrete consulta){

        return ctService.saveConsultaFrete(consulta);

    }
    @ApiOperation(value="Retorna uma consulta de frete não salva no banco passado argumentos direto na url")
    @GetMapping("{peso}/{cepOrigem}/{cepDestino}/{nomeDestinatario}")
    public ResponseEntity<String> buscaConsultaFrete(@PathVariable(value="peso") double peso,
                                             @PathVariable(value="cepOrigem") String cepOrigem,
                                             @PathVariable(value="cepDestino") String cepDestino,
                                             @PathVariable(value="nomeDestinatario") String nomeDestinatario,
                                             @Valid @RequestBody ConsultaFrete consulta) throws JsonProcessingException {

        //procura os dois endereços pelo cep
        ResponseEntity<Endereco> tmpOr = edService.getEnderecoByCep(cepOrigem);
        ResponseEntity<Endereco> tmpDst = edService.getEnderecoByCep(cepDestino);

        consulta.setCepOrigem(tmpOr.getBody().getCep());
        consulta.setCepDestino(tmpDst.getBody().getCep());
        consulta.setPeso(peso);
        consulta.setNomeDestinatario(nomeDestinatario);

        //faz o calculo do frete
        ConsultaFrete tmp = CalcFrete.calcularFrete(peso,tmpOr.getBody(),tmpDst.getBody());


        consulta.setDataPrevista(tmp.getDataPrevista());
        consulta.setDataConsulta(tmp.getDataConsulta());
        consulta.setTotalFrete(tmp.getTotalFrete());

        ObjectMapper objectMapper = new ObjectMapper();

        var json = objectMapper.writeValueAsString(consulta);
        return ResponseEntity.ok().body(json);
    }

    @ApiOperation(value="Retorna uma consulta de frete salva no banco passado argumentos direto na url")
    @PostMapping("{peso}/{cepOrigem}/{cepDestino}/{nomeDestinatario}")
    public ConsultaFrete createConsultaFrete(@PathVariable(value="peso") double peso,
                                    @PathVariable(value="cepOrigem") String cepOrigem,
                                    @PathVariable(value="cepDestino") String cepDestino,
                                    @PathVariable(value="nomeDestinatario") String nomeDestinatario,
                                    @Valid @RequestBody ConsultaFrete consulta){

        //procura os dois endereços pelo cep
        ResponseEntity<Endereco> tmpOr = edService.getEnderecoByCep(cepOrigem);
        ResponseEntity<Endereco> tmpDst = edService.getEnderecoByCep(cepDestino);

        //coloca os dois ceps dentro da consulta
        consulta.setCepOrigem(tmpOr.getBody().getCep());
        consulta.setCepDestino(tmpDst.getBody().getCep());
        consulta.setPeso(peso);
        consulta.setNomeDestinatario(nomeDestinatario);


        //faz o calculo do frete
        ConsultaFrete tmp = CalcFrete.calcularFrete(peso,tmpOr.getBody(),tmpDst.getBody());


        consulta.setDataPrevista(tmp.getDataPrevista());
        consulta.setDataConsulta(tmp.getDataConsulta());
        consulta.setTotalFrete(tmp.getTotalFrete());

        return ctService.saveConsultaFrete(consulta);

    }


    @ApiOperation(value="Exclui uma consulta no banco de dados através de um Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsultaFrete(@PathVariable(value="id") Long id){
        ConsultaFrete tmp = ctService.getConsultaFreteById(id).getBody();
        if(tmp!=null){

            ctService.deleteConsultaFrete(id);
            return ok().body("Conssulta "+id+"apagada com sucesso");
        }else{
            throw new ConsultaFreteNotFoundException(id.toString());
        }
    }

}
