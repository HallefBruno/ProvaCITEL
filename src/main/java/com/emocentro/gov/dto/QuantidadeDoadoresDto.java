package com.emocentro.gov.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class QuantidadeDoadoresDto implements Serializable {
    
    private String receptor;
    private Integer quantidadeDoadores;

    public QuantidadeDoadoresDto(String receptor, Integer quantidadeDoadores) {
        this.receptor = receptor;
        this.quantidadeDoadores = quantidadeDoadores;
    }

    public QuantidadeDoadoresDto() {
    }
    
    
}
