package com.emocentro.gov.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class MediaIdadeSanguineoDto implements Serializable {
    
    private String tipoSanguineo;
    private Integer mediaIdade;

    public MediaIdadeSanguineoDto(String tipoSanguineo, Integer mediaIdade) {
        this.tipoSanguineo = tipoSanguineo;
        this.mediaIdade = mediaIdade;
    }

    public MediaIdadeSanguineoDto() {
    }
    
    
}
