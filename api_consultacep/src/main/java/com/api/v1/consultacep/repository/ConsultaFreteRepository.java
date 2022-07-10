package com.api.v1.consultacep.repository;

import com.api.v1.consultacep.entity.ConsultaFrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaFreteRepository extends JpaRepository<ConsultaFrete,Long> {
}
