package com.ojs.estudosspringmaven.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ojs.estudosspringmaven.models.Cliente;

public interface ClienteService {

	public Page<Cliente> findAll(Pageable paginacao);
	public Optional<Cliente> find(Long id);
	public Cliente create(Cliente cliente);
	public Cliente update(Long id, Cliente cliente);
	public void delete(Long id);
	
}
