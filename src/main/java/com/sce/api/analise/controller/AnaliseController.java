package com.sce.api.analise.controller;

import static com.sce.api.ApiConstants.LOG_REQUEST_INVALIDA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.analise.exception.AnaliseApiException;
import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.analise.model.AnaliseVo;
import com.sce.api.analise.service.AnaliseRequestValidator;
import com.sce.api.analise.service.AnaliseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sce")
@Slf4j
public class AnaliseController {

	@Autowired
	private AnaliseService analiseService;
	@Autowired
	private AnaliseRequestValidator validator;
	
	@PostMapping("/analise")
	public ResponseEntity<AnaliseResponse> create(@RequestBody AnaliseVo vo) throws AnaliseApiException {
	    checkRequest(vo);
		AnaliseResponse response = analiseService.save(vo);
		return ResponseEntity.ok(response);
	}
	
	private void checkRequest(AnaliseVo vo) throws AnaliseApiException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(vo, AnaliseVo.class.getName());
        ValidationUtils.invokeValidator(validator, vo, bindingResult);
        if (bindingResult.hasErrors()) {
            log.warn(LOG_REQUEST_INVALIDA);
            throw new AnaliseApiException(LOG_REQUEST_INVALIDA, bindingResult);
        }
    }
}
