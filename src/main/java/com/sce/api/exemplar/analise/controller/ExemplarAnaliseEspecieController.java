package com.sce.api.exemplar.analise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.exemplar.analise.exception.ExemplarAnaliseApiException;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieResponse;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieVo;
import com.sce.api.exemplar.analise.service.ExemplarAnaliseEspecieService;

@RestController
@RequestMapping("/v1/sce")
public class ExemplarAnaliseEspecieController {

	@Autowired
	private ExemplarAnaliseEspecieService exemplarAnaliseService;
	
	@PostMapping("/exemplarAnalise")
	public ResponseEntity<ExemplarAnaliseEspecieResponse> create(@RequestBody ExemplarAnaliseEspecieVo vo) throws ExemplarAnaliseApiException {
	    ExemplarAnaliseEspecieResponse response = exemplarAnaliseService.save(vo);
		return ResponseEntity.ok(response);
	}
}
