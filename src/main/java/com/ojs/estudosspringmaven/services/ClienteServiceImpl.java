package com.ojs.estudosspringmaven.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojs.estudosspringmaven.models.Cliente;
import com.ojs.estudosspringmaven.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> find(Long id) {
		return this.clienteRepository.findById(id);
	}

	@Override
	public Cliente create(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	@Override
	public Cliente update(Long id, Cliente cliente) {

		Optional<Cliente> clienteCorrente = this.clienteRepository.findById(id);

		if (clienteCorrente.isPresent()) {
			cliente.setId(clienteCorrente.get().getId());
			return this.clienteRepository.save(cliente);
		}

		return null;

		// lambda?? TODO estudar o que é isso
//		Optional<Cliente> clienteAtualizado = this.clienteRepository.findById(id).map(record -> {
//			record.setCidade(cliente.getCidade());
//
//			Cliente updated = this.clienteRepository.save(record);
//
//			return updated;
//		});
//
//		return clienteAtualizado;
	}

	@Override
	public void delete(Long id) {
		this.clienteRepository.deleteById(id);
	}

}
