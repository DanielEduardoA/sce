package com.sce.api.vistoria.controller;

import static com.sce.api.ApiConstants.LOG_REQUEST_INVALIDA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.vistoria.exception.VistoriaApiException;
import com.sce.api.vistoria.service.VistoriaRequestValidator;
import com.sce.api.vistoria.service.VistoriaService;
import com.sce.api.vistoria.vo.VistoriaResponse;
import com.sce.api.vistoria.vo.VistoriaVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sce")
@Slf4j
public class VistoriaController {

    @Autowired
    private VistoriaService vistoriaService;
    @Autowired
    private VistoriaRequestValidator validator;

    @PostMapping("/vistoria")
    public ResponseEntity<VistoriaResponse> create(@RequestBody VistoriaVo vo) throws VistoriaApiException {
        checkRequest(vo);
        VistoriaResponse response = vistoriaService.save(vo);
        return ResponseEntity.ok(response);
    }
    
    private void checkRequest(VistoriaVo vo) throws VistoriaApiException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(vo, VistoriaVo.class.getName());
        ValidationUtils.invokeValidator(validator, vo, bindingResult);
        if (bindingResult.hasErrors()) {
            log.warn(LOG_REQUEST_INVALIDA);
            throw new VistoriaApiException(LOG_REQUEST_INVALIDA, bindingResult);
        }
    }

}
