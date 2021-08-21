package com.sce.api.deposito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.deposito.model.DepositoResponse;
import com.sce.api.deposito.service.DepositoService;

@RestController
@RequestMapping("/v1/sce")
public class DepositoController {
	
	@Autowired
	private DepositoService depositoService;
	
	@GetMapping("/deposito")
	public ResponseEntity<List<DepositoResponse>> findAll() {
		List<DepositoResponse> response = depositoService.listAll();
		return ResponseEntity.ok(response);
	}

}
