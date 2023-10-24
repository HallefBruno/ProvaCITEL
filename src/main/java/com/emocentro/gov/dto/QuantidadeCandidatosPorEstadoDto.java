package com.emocentro.gov.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class QuantidadeCandidatosPorEstadoDto implements Serializable {
    
    private Integer total;
    private String estado;

    public QuantidadeCandidatosPorEstadoDto(Integer total, String estado) {
        this.total = total;
        this.estado = estado;
    }

    public QuantidadeCandidatosPorEstadoDto() {
    }
    
    
}
