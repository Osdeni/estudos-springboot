package com.ojs.estudosspringmaven.resources.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ojs.estudosspringmaven.config.exception.ResourceNotFoundException;
import com.ojs.estudosspringmaven.models.Cliente;
import com.ojs.estudosspringmaven.models.ClienteDados;
import com.ojs.estudosspringmaven.services.ClienteDadosService;
import com.ojs.estudosspringmaven.services.ClienteService;
import com.ojs.estudosspringmaven.util.ClienteDadosTipos;

public class ClienteDadosDto {
	private Long id;

	@JsonIgnore
	private Cliente cliente;

	private ClienteDadosTipos tipo;

	@NotNull
	@NotBlank
	@Length(min = 3)
	private String chave;

	@NotNull
	@NotBlank
	@Length(min = 3)
	private String valor;

	public ClienteDadosDto() {

	}

	public ClienteDadosDto(ClienteDados clienteDados) {
		this.id = clienteDados.getId();
		this.cliente = clienteDados.getCliente();
		this.tipo = clienteDados.getTipo();
		this.chave = clienteDados.getChave();
		this.valor = clienteDados.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDadosTipos getTipo() {
		return tipo;
	}

	public void setTipo(ClienteDadosTipos tipo) {
		this.tipo = tipo;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ClienteDados converter(Long id, Long clienteId, ClienteService clienteService, ClienteDadosService clienteDadosService)
			throws ResourceNotFoundException {
		
		Optional<ClienteDados> clienteDados = clienteDadosService.find(clienteId, id);

		if (!clienteDados.isPresent()) {
			throw new ResourceNotFoundException("Dado de id: " + id + ", do cliente: " + clienteId + ", não foi encontrado");
		}
		
		this.id = id;
		return this.converter(clienteId, clienteService);
	}

	public ClienteDados converter(Long clienteId, ClienteService clienteService) throws ResourceNotFoundException {

		Optional<Cliente> cliente = clienteService.find(clienteId);

		if (!cliente.isPresent()) {
			throw new ResourceNotFoundException("Cliente não encontrado: " + clienteId);
		}

		return new ClienteDados(cliente.get(), this.getTipo(), this.getChave(), this.getValor());
	}

}
