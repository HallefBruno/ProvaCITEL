package com.emocentro.gov.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class RelatorioDto implements Serializable {
    
    private List<QuantidadeCandidatosPorEstadoDto> quantidadeCandidatosPorEstado;
    private List<ImcMedioDto> imcMedio;
    private List<ObesosHomemMulherDto> obesosHomemMulher;
    private List<MediaIdadeSanguineoDto> mediaIdadeSanguineo;
    private List<QuantidadeDoadoresDto> quantidadeDoadores;

    public RelatorioDto(List<QuantidadeCandidatosPorEstadoDto> quantidadeCandidatosPorEstado, List<ImcMedioDto> imcMedio, List<ObesosHomemMulherDto> obesosHomemMulher, List<MediaIdadeSanguineoDto> mediaIdadeSanguineo, List<QuantidadeDoadoresDto> quantidadeDoadores) {
        this.quantidadeCandidatosPorEstado = quantidadeCandidatosPorEstado;
        this.imcMedio = imcMedio;
        this.obesosHomemMulher = obesosHomemMulher;
        this.mediaIdadeSanguineo = mediaIdadeSanguineo;
        this.quantidadeDoadores = quantidadeDoadores;
    }

    public RelatorioDto() {
    }
    
}
