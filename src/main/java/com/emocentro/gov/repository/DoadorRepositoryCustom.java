package com.emocentro.gov.repository;

import com.emocentro.gov.dto.ImcMedioDto;
import com.emocentro.gov.dto.MediaIdadeSanguineoDto;
import com.emocentro.gov.dto.ObesosHomemMulherDto;
import com.emocentro.gov.dto.QuantidadeCandidatosPorEstadoDto;
import com.emocentro.gov.dto.QuantidadeDoadoresDto;
import java.util.List;


public interface DoadorRepositoryCustom {
    List<QuantidadeCandidatosPorEstadoDto> quantidadeCandidatosPorEstado();
    List<ImcMedioDto> imcMedio();
    List<ObesosHomemMulherDto> obesosHomemMulher();
    List<MediaIdadeSanguineoDto> mediaIdadeSanguineo();
    List<QuantidadeDoadoresDto> quantidadeDoadores();
}
