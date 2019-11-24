package com.ojs.estudosspringmaven.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ojs.estudosspringmaven.util.Estados;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 2, nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private Estados estado;

	@Column(length = 50, nullable = false, unique = true)
	@NotEmpty(message = "Não deve ser nulo")
	@NotBlank(message = "Não deve ser vazio")
	@Size(min = 3, max = 50)
	private String cidade;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
