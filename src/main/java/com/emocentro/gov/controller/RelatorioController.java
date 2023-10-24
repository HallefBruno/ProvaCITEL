package com.emocentro.gov.controller;

import com.emocentro.gov.dto.RelatorioDto;
import com.emocentro.gov.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emocentro")
public class RelatorioController {
    
    @Autowired
    private DoadorService doadorService;
    
    @GetMapping("/relatorio")
    public ResponseEntity<RelatorioDto> relatorio() {
        return ResponseEntity.ok(doadorService.relatorio());
    }
    
}
