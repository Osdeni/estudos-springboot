package com.ojs.estudosspringmaven.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ojs.estudosspringmaven.util.ClienteDadosTipos;

@Entity
public class ClienteDados {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ClienteDadosTipos tipo;

	@Column(nullable = false)
	private String chave;

	@Column(nullable = false)
	private String valor;

	public ClienteDados() {

	}

	public ClienteDados(Cliente cliente, ClienteDadosTipos tipo, String chave, String valor) {
		this.cliente = cliente;
		this.tipo = tipo;
		this.chave = chave;
		this.valor = valor;
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

}
