package com.emocentro.gov.service;

import com.emocentro.gov.dto.DoadorDto;
import com.emocentro.gov.dto.RelatorioDto;
import com.emocentro.gov.entity.Doador;
import com.emocentro.gov.repository.DoadorRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.apache.commons.lang3.StringUtils.getDigits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DoadorService {
    
    @Autowired
    private DoadorRepository doadorRepository;
    
    @Autowired
    private ImportarArquivoJsonService importarArquivoJsonService;
    
    @Transactional
    public String salvar(MultipartFile file) {
        List<DoadorDto> list = importarArquivoJsonService.converterArquivoParaObjeto(file);
        List<Doador> doadores = new ArrayList<>();
        Doador doador;
        for(DoadorDto dto : list) {
            if(!existeDoador(dto.getCpf())) {
                doador = new Doador();
                doador.setNome(dto.getNome());
                doador.setCpf(dto.getCpf());
                doador.setRg(dto.getRg());
                checkDataNascimento(dto.getDataNascimento(), doador);
                doador.setSexo(dto.getSexo());
                doador.setMae(dto.getMae());
                doador.setPai(dto.getPai());
                doador.setEmail(dto.getEmail());
                doador.setCep(dto.getCep());
                doador.setEndereco(dto.getEndereco());
                doador.setNumero(dto.getNumero());
                doador.setBairro(dto.getBairro());
                doador.setCidade(dto.getCidade());
                doador.setEstado(dto.getEstado());
                doador.setTelefone(dto.getTelefone());
                doador.setCelular(dto.getCelular());
                doador.setAltura(dto.getAltura());
                doador.setPeso(dto.getPeso());
                doador.setTipoSanguineo(dto.getTipoSanguineo());
                doadores.add(doador);
            }
        }
        doadorRepository.saveAll(doadores);
        
        if (doadores.isEmpty()) {
            return "Todos os registro desse arquivo já estavam na base de dados!";
        } else if (doadores.size() == list.size()) {
            return "Registros salvo com sucesso!";
        } else {
            return "Alguns registro desse arquivo foram ignorados porque ja estava na base de dados os demias foram salvos!";
        }
    }
    
    
    private boolean existeDoador(String cpf) {
        return doadorRepository.existsByCpf(getDigits(cpf));
    }
    
    private void checkDataNascimento(String dataNascimento, Doador doador) {
        if (Objects.nonNull(dataNascimento) && !dataNascimento.isBlank()) {
            doador.setDataNascimento(LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            doador.setDataNascimento(null);
        }
    }
    
    public RelatorioDto relatorio() {
        RelatorioDto relatorioDto = new RelatorioDto(
doadorRepository.quantidadeCandidatosPorEstado(),
                doadorRepository.imcMedio(), 
         doadorRepository.obesosHomemMulher(), 
        doadorRepository.mediaIdadeSanguineo(), 
        doadorRepository.quantidadeDoadores());
        return relatorioDto;
    }
    
//    Doador probe = new Doador();
//    probe.setCpf("00000000000");
//    Example<Doador> example = Example.of(probe, ExampleMatcher.matching());
//    boolean exists = repository.exists(example);
    
}
