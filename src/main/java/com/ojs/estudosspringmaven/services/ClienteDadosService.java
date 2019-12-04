package com.ojs.estudosspringmaven.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.ojs.estudosspringmaven.config.exception.ResourceNotFoundException;
import com.ojs.estudosspringmaven.models.ClienteDados;

public interface ClienteDadosService {

	public List<ClienteDados> findAll(Long clienteId, Sort sortable);

	public Optional<ClienteDados> find(Long clienteId, Long id);

	public ClienteDados create(ClienteDados clienteDados);

	public ClienteDados update(Long id, ClienteDados clienteDados);

	public void delete(Long clienteId, Long id) throws ResourceNotFoundException;

}
