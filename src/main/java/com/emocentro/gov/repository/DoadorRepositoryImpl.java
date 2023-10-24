package com.emocentro.gov.repository;

import com.emocentro.gov.dto.ImcMedioDto;
import com.emocentro.gov.dto.MediaIdadeSanguineoDto;
import com.emocentro.gov.dto.ObesosHomemMulherDto;
import com.emocentro.gov.dto.QuantidadeCandidatosPorEstadoDto;
import com.emocentro.gov.dto.QuantidadeDoadoresDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

public class DoadorRepositoryImpl implements DoadorRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuantidadeCandidatosPorEstadoDto> quantidadeCandidatosPorEstado() {
        List<QuantidadeCandidatosPorEstadoDto> dados = entityManager.createNamedQuery("QuantidadeCandidatosPorEstadoDto", QuantidadeCandidatosPorEstadoDto.class).getResultList();
        return retornaDadosOuListaVazia(dados);
    }

    @Override
    public List<ImcMedioDto> imcMedio() {
        List<ImcMedioDto> dados = entityManager.createNamedQuery("ImcMedioDto", ImcMedioDto.class).getResultList();
        return retornaDadosOuListaVazia(dados);
    }

    @Override
    public List<ObesosHomemMulherDto> obesosHomemMulher() {
        List<ObesosHomemMulherDto> dados = entityManager.createNamedQuery("ObesosHomemMulherDto", ObesosHomemMulherDto.class).getResultList();
        return retornaDadosOuListaVazia(dados);
    }

    @Override
    public List<MediaIdadeSanguineoDto> mediaIdadeSanguineo() {
        List<MediaIdadeSanguineoDto> dados = entityManager.createNamedQuery("MediaIdadeSanguineoDto", MediaIdadeSanguineoDto.class).getResultList();
        return retornaDadosOuListaVazia(dados);
    }

    @Override
    public List<QuantidadeDoadoresDto> quantidadeDoadores() {
        List<QuantidadeDoadoresDto> dados = entityManager.createNamedQuery("QuantidadeDoadoresDto", QuantidadeDoadoresDto.class).getResultList();
        return retornaDadosOuListaVazia(dados);
    }
    
    private <T> List<T> retornaDadosOuListaVazia(List<T> dados) {
        if (dados == null) {
            return Collections.emptyList();
        }
        return dados;
    }

}
