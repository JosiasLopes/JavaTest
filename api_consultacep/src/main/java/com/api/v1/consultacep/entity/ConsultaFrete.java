package com.api.v1.consultacep.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown
        = true)
@Entity
@Table(name ="ConsultaFrete")
@Data
public class ConsultaFrete {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("cepOrigem")
    @Column(name ="cepOrigem")
    private String cepOrigem;

    @JsonProperty("cepDestino")
    @Column(name ="cepDestino")
    private String cepDestino;

    @JsonIgnore
    @Column(name ="nomeDestinatario")
    private String nomeDestinatario;

    @JsonIgnore
    @Column(name ="peso")
    private double peso;

    @JsonProperty("vlTotalFrete")
    @Column(name ="totalFrete")
    private double totalFrete;

    @JsonProperty("dataPrevistaEntrega")
    @Column(name ="dataPrevista")
    private String dataPrevista;

    @JsonIgnore
    @Column(name ="dataConsulta")
    private String dataConsulta;

    @Override
    public String toString() {
        return "ConsultaFrete[" +
                "id=" + id +
                ", dataPrevista='" + dataPrevista +
                ", dataConsulta='" + dataConsulta +
                "vlTotalFrete'" + totalFrete  +
                ", nomeDestinatario='" + nomeDestinatario +
                ", peso='" + peso +
                ", cepDeOrigem='" + cepOrigem +
                ", cepDeDestino='" + cepDestino +
                ']';
    }

  //  @OneToMany
  //  private List<Endereco> enderecos;


}
