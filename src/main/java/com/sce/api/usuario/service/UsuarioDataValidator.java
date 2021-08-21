package com.sce.api.usuario.service;

import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.usuario.exception.UsuarioApiException;
import com.sce.api.usuario.model.UsuarioVo;
import com.sce.persistence.usuario.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioDataValidator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validateUser(UsuarioVo vo) throws UsuarioApiException {
        if (usuarioRepository.findByLoginOrEmail(vo.getLogin(), vo.getEmail()).isPresent()) {
            log.warn("Login ou email já existe na base de dados.");
            throw new UsuarioApiException("Login ou email já existe na base de dados.", CONFLICT);
        }
    }
}
