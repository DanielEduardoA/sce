package com.sce.api.exemplar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.exemplar.model.ExemplarResponse;
import com.sce.api.exemplar.service.ExemplarService;


@RestController
@RequestMapping("/v1/sce")
public class ExemplarController {

	@Autowired
	private ExemplarService exemplarService;
	
	@GetMapping("/exemplar")
	public ResponseEntity<List<ExemplarResponse>> create() {
		List<ExemplarResponse> response = exemplarService.listAll();
		return ResponseEntity.ok(response);
	}
}
