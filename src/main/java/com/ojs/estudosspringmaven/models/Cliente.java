package com.ojs.estudosspringmaven.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(length = 2, nullable = false, name = "estado")
	@NotNull
	@Enumerated(EnumType.STRING)
	private Estados estado;

	@Column(length = 50, nullable = false, unique = true)
	@NotEmpty
	@NotBlank
	@Size(min = 3, max = 50)
	private String cidade;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<ClienteDados> dados = new HashSet<ClienteDados>();

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

	public Set<ClienteDados> getDados() {
		return dados;
	}

	public void setDados(Set<ClienteDados> dados) {
		this.dados = dados;
	}

}
