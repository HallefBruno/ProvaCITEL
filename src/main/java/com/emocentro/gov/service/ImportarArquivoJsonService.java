package com.emocentro.gov.service;

import com.emocentro.gov.dto.DoadorDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ImportarArquivoJsonService {
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public List<DoadorDto> converterArquivoParaObjeto(MultipartFile file) {
        try {
            if(Objects.nonNull(file) && file.getBytes().length > 0) {
                if(Objects.nonNull(file.getContentType()) && file.getContentType().toLowerCase().contains("json")) {
                    String arquivo = new String(file.getBytes(), StandardCharsets.UTF_8);
                    List<DoadorDto> doadorDto = objectMapper.readValue(arquivo, new TypeReference<List<DoadorDto>>(){});
                    return doadorDto;
                }
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arquivo inválido!");
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um erro ocorreu ao converter o arquivo!");
        }
    }
}
