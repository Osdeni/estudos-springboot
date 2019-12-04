package com.ojs.estudosspringmaven.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojs.estudosspringmaven.models.ClienteDados;

@Repository
public interface ClienteDadosRepository extends JpaRepository<ClienteDados, Long> {

	List<ClienteDados> findAllByClienteId(Long clienteId, Sort sortable);

	Optional<ClienteDados> findByIdAndClienteId(Long id, Long clienteId);
}
