package com.ojs.estudosspringmaven.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ojs.estudosspringmaven.config.exception.ResourceNotFoundException;
import com.ojs.estudosspringmaven.models.Cliente;
import com.ojs.estudosspringmaven.services.ClienteService;
import com.ojs.estudosspringmaven.util.Estados;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/clientes")
@Api(value = "API Clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public Page<Cliente> findAll(@PageableDefault(sort = {"estado", "cidade"}) Pageable paginacao) {
		return this.clienteService.findAll(paginacao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Long id) throws ResourceNotFoundException {

		// TODO optional isPresent
//		Optional <Cliente> c = findById();
//		if (c.isPresent()) {
//			return ResponseEntity.ok(dto c.get());
//		}
//		
//		return ResponseEntity.notFound().build();

		// TODO DTO saída (pode ser específico) com mais dados.
		Cliente cliente = this.clienteService.find(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));

		return ResponseEntity.ok().body(cliente);
	}

	@PostMapping
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente, UriComponentsBuilder uriBuilder) {

		// TODO DTO entrada form
		// TODO Service IMPL precisa mesmo?

		try {
			Cliente clienteCriado = this.clienteService.create(cliente);

			URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteCriado.getId()).toUri();
			return ResponseEntity.created(uri).body(clienteCriado);
		} catch (DataIntegrityViolationException e) {
			// TODO tratamento do unique?
			return new ResponseEntity<Cliente>(cliente, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public Cliente update(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
		return this.clienteService.update(id, cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		// TODO response entity.ok??

		this.clienteService.delete(id);
	}

}
