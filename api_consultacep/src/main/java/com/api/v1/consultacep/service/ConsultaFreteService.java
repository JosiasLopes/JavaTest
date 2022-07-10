package com.api.v1.consultacep.service;


import com.api.v1.consultacep.exception.ConsultaFreteNotFoundException;
import com.api.v1.consultacep.repository.ConsultaFreteRepository;
import com.api.v1.consultacep.entity.ConsultaFrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaFreteService {

    @Autowired
    ConsultaFreteRepository ctRepo;

    public List<ConsultaFrete> getAllConsultaFrete(){
        return this.ctRepo.findAll();
    }

    public ResponseEntity<ConsultaFrete> getConsultaFreteById(Long id){
        Optional<ConsultaFrete> opt = ctRepo.findById(id);
        if(opt.isPresent()){

            return ResponseEntity.ok().body(opt.get());
        }else{
            throw new ConsultaFreteNotFoundException(id);
        }
    }

    public ConsultaFrete saveConsultaFrete(ConsultaFrete consulta) {
        return ctRepo.save(consulta);
    }

    public void deleteConsultaFrete(Long id) {
        ctRepo.deleteById(id);
    }

    public ResponseEntity<?> deleteConsultaFreteById(Long id){
        Optional<ConsultaFrete> opt = ctRepo.findById(id);
        if(opt.isPresent()){
            ConsultaFrete tmp = opt.get();
            ctRepo.deleteById(tmp.getId());
            return ResponseEntity.ok().body("Consulta "+tmp.getId()+" excluida com sucesso");
        }else{
            throw new ConsultaFreteNotFoundException(id);
        }
    }
}
