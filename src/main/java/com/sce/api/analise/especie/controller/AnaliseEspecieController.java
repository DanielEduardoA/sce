package com.sce.api.analise.especie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.analise.especie.exception.AnaliseEspecieApiException;
import com.sce.api.analise.especie.model.AnaliseEspecieResponse;
import com.sce.api.analise.especie.model.AnaliseEspecieVo;
import com.sce.api.analise.especie.service.AnaliseEspecieService;

@RestController
@RequestMapping("/v1/sce")
public class AnaliseEspecieController {

    @Autowired
    private AnaliseEspecieService analiseEspecieService;

    @PostMapping("/analiseEspecie")
    public ResponseEntity<AnaliseEspecieResponse> create(@RequestBody AnaliseEspecieVo vo) throws AnaliseEspecieApiException {
        AnaliseEspecieResponse response = analiseEspecieService.save(vo);
        return ResponseEntity.ok(response);
    }
}
