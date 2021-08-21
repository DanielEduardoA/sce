package com.sce.api.especie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.especie.model.EspecieResponse;
import com.sce.api.especie.service.EspecieService;

@RestController
@RequestMapping("/v1/sce")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping("/especie")
    public ResponseEntity<List<EspecieResponse>> findAll() {
        List<EspecieResponse> response = especieService.listAll();
        return ResponseEntity.ok(response);
    }

}
