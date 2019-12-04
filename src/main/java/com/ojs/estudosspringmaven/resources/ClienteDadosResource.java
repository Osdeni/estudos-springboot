package com.ojs.estudosspringmaven.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ojs.estudosspringmaven.config.exception.ResourceNotFoundException;
import com.ojs.estudosspringmaven.models.ClienteDados;
import com.ojs.estudosspringmaven.resources.dto.ClienteDadosDto;
import com.ojs.estudosspringmaven.services.ClienteDadosService;
import com.ojs.estudosspringmaven.services.ClienteService;

@RestController
@RequestMapping("/clientes/{clienteId}/dados")
public class ClienteDadosResource {

	@Autowired
	ClienteDadosService clienteDadosService;

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public List<ClienteDados> findAll(@PathVariable Long clienteId) {
		return this.clienteDadosService.findAll(clienteId, Sort.by(Sort.Direction.ASC, "tipo"));
	}

	@PostMapping
	public ResponseEntity<ClienteDadosDto> create(@PathVariable Long clienteId,
			@RequestBody @Valid ClienteDadosDto form, UriComponentsBuilder uriBuilder)
			throws ResourceNotFoundException {

		ClienteDados clienteDados = form.converter(clienteId, clienteService);
		ClienteDados clienteDadosCriado = clienteDadosService.create(clienteDados);

		URI uri = uriBuilder.path("clientes/{clienteId}").buildAndExpand(clienteId).toUri();
		return ResponseEntity.created(uri).body(new ClienteDadosDto(clienteDadosCriado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDadosDto> update(@PathVariable Long clienteId, @PathVariable Long id,
			@RequestBody @Valid ClienteDadosDto form) throws ResourceNotFoundException {
		ClienteDados clienteDados = form.converter(id, clienteId, clienteService, clienteDadosService);

		ClienteDados clienteDadosAtualizado = clienteDadosService.update(id, clienteDados);

		return ResponseEntity.ok().body(new ClienteDadosDto(clienteDadosAtualizado));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long clienteId, @PathVariable Long id) throws ResourceNotFoundException {
		clienteDadosService.delete(clienteId, id);
	}

}
