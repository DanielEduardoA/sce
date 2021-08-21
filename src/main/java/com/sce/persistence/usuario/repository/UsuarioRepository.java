package com.sce.persistence.usuario.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.usuario.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByLoginAndSenha(String login, String senha);
	
	Optional<Usuario> findByLoginOrEmail(String login, String email);
}
