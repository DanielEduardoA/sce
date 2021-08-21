package com.sce.api.usuario.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.usuario.exception.UsuarioApiException;
import com.sce.api.usuario.model.UsuarioResponse;
import com.sce.api.usuario.model.UsuarioVo;
import com.sce.persistence.usuario.entity.Usuario;
import com.sce.persistence.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDataValidator dataValidator;

    public UsuarioResponse save(UsuarioVo vo) throws NoSuchAlgorithmException, UsuarioApiException {
        dataValidator.validateUser(vo);
        Usuario usuario = buildUsuario(vo);
        return buildUsuarioResponse(usuarioRepository.save(usuario));
    }

    private Usuario buildUsuario(UsuarioVo vo) throws NoSuchAlgorithmException {
        return Usuario.builder()
                .email(vo.getEmail())
                .login(vo.getLogin())
                .nome(vo.getNome())
                .senha(generateMD5Hash(vo.getSenha()))
                .telefone(vo.getTelefone())
                .tipo(vo.getTipo())
                .build();
    }

    private UsuarioResponse buildUsuarioResponse(Usuario usuario) throws NoSuchAlgorithmException {
        return UsuarioResponse.builder()
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .tipo(usuario.getTipo())
                .id(usuario.getId())
                .build();
    }

    private String generateMD5Hash(String senha) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
