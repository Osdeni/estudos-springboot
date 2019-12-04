package com.ojs.estudosspringmaven.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojs.estudosspringmaven.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
}
