package com.sce.api.deposito.vistoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.deposito.vistoria.exception.DepositoVistoriaApiException;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaResponse;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaVo;
import com.sce.api.deposito.vistoria.service.DepositoVistoriaService;

@RestController
@RequestMapping("/v1/sce")
public class DepositoVistoriaController {

	@Autowired
	private DepositoVistoriaService depositoVistoriaService;
	
	@PostMapping("/depositoVistoria")
	public ResponseEntity<DepositoVistoriaResponse> create(@RequestBody DepositoVistoriaVo vo) throws DepositoVistoriaApiException {
	    DepositoVistoriaResponse reponse = depositoVistoriaService.save(vo);
		return ResponseEntity.ok(reponse);
	}

}
