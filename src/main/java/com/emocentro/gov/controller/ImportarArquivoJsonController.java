package com.emocentro.gov.controller;

import com.emocentro.gov.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/emocentro")
public class ImportarArquivoJsonController {
    
    @Autowired
    private DoadorService doadorService;
    
    @PostMapping("/json-file-upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String mensagem = doadorService.salvar(file);
        return ResponseEntity.ok(mensagem);
    }
    
}
