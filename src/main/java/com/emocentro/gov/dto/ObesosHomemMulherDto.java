package com.emocentro.gov.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ObesosHomemMulherDto implements Serializable {
    
    private String sexo;
    private Integer obesos;
    private Integer total;
    private Double percentual;

    public ObesosHomemMulherDto(String sexo, Integer obesos, Integer total, Double percentual) {
        this.sexo = sexo;
        this.obesos = obesos;
        this.total = total;
        this.percentual = percentual;
    }

    public ObesosHomemMulherDto() {
    }
    
    
}
