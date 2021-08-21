package com.sce.api.amostra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.amostra.exception.AmostraApiException;
import com.sce.api.amostra.model.AmostraResponse;
import com.sce.api.amostra.model.AmostraVo;
import com.sce.api.amostra.service.AmostraService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sce")
@Slf4j
public class AmostraController {

    @Autowired
    private AmostraService amostraService;

    @PostMapping("/amostra")
    public ResponseEntity<AmostraResponse> postAmostra(@RequestBody AmostraVo vo) throws AmostraApiException {
        log.info("Criando amostra");
        AmostraResponse response = amostraService.save(vo);
        return ResponseEntity.ok(response);
    }

}
