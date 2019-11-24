package com.ojs.estudosspringmaven.services;

import java.util.List;
import java.util.Optional;

import com.ojs.estudosspringmaven.models.Cliente;

public interface ClienteService {

	public List<Cliente> findAll();
	public Optional<Cliente> find(Long id);
	public Cliente create(Cliente cliente);
	public Cliente update(Long id, Cliente cliente);
	public void delete(Long id);
	
}
