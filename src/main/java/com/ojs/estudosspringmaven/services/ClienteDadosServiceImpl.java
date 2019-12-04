package com.ojs.estudosspringmaven.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ojs.estudosspringmaven.config.exception.ResourceNotFoundException;
import com.ojs.estudosspringmaven.models.ClienteDados;
import com.ojs.estudosspringmaven.repository.ClienteDadosRepository;

@Service
public class ClienteDadosServiceImpl implements ClienteDadosService {

	@Autowired
	private ClienteDadosRepository clienteDadosRepository;

	public ClienteDadosServiceImpl(ClienteDadosRepository clienteDadosRepository) {
		this.clienteDadosRepository = clienteDadosRepository;
	}

	@Override
	public List<ClienteDados> findAll(Long clienteId, Sort sortable) {
		return this.clienteDadosRepository.findAllByClienteId(clienteId, sortable);
	}

	@Override
	public Optional<ClienteDados> find(Long clienteId, Long id) {
		return this.clienteDadosRepository.findByIdAndClienteId(id, clienteId);
	}

	@Override
	public ClienteDados create(ClienteDados clienteDados) {
		return this.clienteDadosRepository.save(clienteDados);
	}

	@Override
	public ClienteDados update(Long id, ClienteDados clienteDados) {

		Optional<ClienteDados> clienteDadosCorrente = this.clienteDadosRepository.findByIdAndClienteId(id,
				clienteDados.getCliente().getId());
		if (clienteDadosCorrente.isPresent()) {
			clienteDados.setId(clienteDadosCorrente.get().getId());
			return this.clienteDadosRepository.save(clienteDados);
		}

		return null;
	}

	@Override
	public void delete(Long clienteId, Long id) throws ResourceNotFoundException {

		Optional<ClienteDados> clienteDadosCorrente = this.clienteDadosRepository.findByIdAndClienteId(id, clienteId);
		if (!clienteDadosCorrente.isPresent()) {
			throw new ResourceNotFoundException(
					"Dado de id: " + id + ", do cliente: " + clienteId + ", não foi encontrado");
		}

		this.clienteDadosRepository.delete(clienteDadosCorrente.get());
	}

}
