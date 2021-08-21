package com.sce.api.tratamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.tratamento.exception.copy.TratamentoApiException;
import com.sce.api.tratamento.model.TratamentoResponse;
import com.sce.api.tratamento.model.TratamentoVo;
import com.sce.api.tratamento.service.TratamentoService;

@RestController
@RequestMapping("/v1/sce")
public class TratamentoController {

	@Autowired
	private TratamentoService tratamentoService;
	
	@PostMapping("/tratamento")
	public ResponseEntity<TratamentoResponse> create(@RequestBody TratamentoVo vo) throws TratamentoApiException {
		TratamentoResponse reponse = tratamentoService.save(vo);
		return ResponseEntity.ok(reponse);
	}
}
