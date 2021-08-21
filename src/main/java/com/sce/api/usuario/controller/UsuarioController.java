package com.sce.api.usuario.controller;

import static com.sce.api.ApiConstants.LOG_REQUEST_INVALIDA;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.usuario.exception.UsuarioApiException;
import com.sce.api.usuario.model.UsuarioResponse;
import com.sce.api.usuario.model.UsuarioVo;
import com.sce.api.usuario.service.UsuarioRequestValidator;
import com.sce.api.usuario.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sce")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRequestValidator validator;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponse> create(@RequestBody UsuarioVo vo) throws NoSuchAlgorithmException, UsuarioApiException {
        checkRequest(vo);
        UsuarioResponse response = usuarioService.save(vo);
        return ResponseEntity.ok(response);
    }

    private void checkRequest(UsuarioVo vo) throws UsuarioApiException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(vo, UsuarioVo.class.getName());
        ValidationUtils.invokeValidator(validator, vo, bindingResult);
        if (bindingResult.hasErrors()) {
            log.warn(LOG_REQUEST_INVALIDA);
            throw new UsuarioApiException(LOG_REQUEST_INVALIDA, bindingResult);
        }
    }

}
