package com.emocentro.gov;

import com.emocentro.gov.service.ImportarArquivoJsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class EmocentroApplicationTests {
    
    @InjectMocks
    ImportarArquivoJsonService importarArquivoJsonService;
    
    @BeforeEach
    void setUpMocks() {
        ReflectionTestUtils.setField(importarArquivoJsonService, "objectMapper", new ObjectMapper());
	MockitoAnnotations.openMocks(this);
    }
    
    
    @Test
    void lancar_erro_caso_arquivo_seja_diferente_do_objeto() {
        MockMultipartFile jsonFile = new MockMultipartFile("test.json", "", "application/json", "{\"key\": \"value\"}".getBytes());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> importarArquivoJsonService.converterArquivoParaObjeto(jsonFile)); 
        assertEquals("Um erro ocorreu ao converter o arquivo!", exception.getReason());
    }
    
    @Test
    void lancar_erro_caso_arquivo_seja_diferente_de_json() {
        MockMultipartFile jsonFile = new MockMultipartFile("test.json", "", "application/text", "{\"key\": \"value\"}".getBytes());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> importarArquivoJsonService.converterArquivoParaObjeto(jsonFile)); 
        assertEquals("Arquivo inválido!", exception.getReason());
    }

}
