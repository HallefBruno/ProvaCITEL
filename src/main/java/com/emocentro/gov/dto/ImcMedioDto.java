package com.emocentro.gov.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ImcMedioDto implements Serializable {
    
    private String faixaIdade;
    private Double imcMedio;

    public ImcMedioDto(String faixaIdade, Double imcMedio) {
        this.faixaIdade = faixaIdade;
        this.imcMedio = imcMedio;
    }

    public ImcMedioDto() {
    }
    
}
