package com.ojs.estudosspringmaven.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ojs.estudosspringmaven.models.Cliente;
import com.ojs.estudosspringmaven.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	@ResponseBody
	public List<Cliente> findAll() {
		return this.clienteService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Cliente> find(@PathVariable("id") Long id) {
		return this.clienteService.find(id);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, Errors errors) {

		if (!errors.hasErrors()) {
			Cliente clienteCriado = this.clienteService.create(cliente);
			return new ResponseEntity<Cliente>(clienteCriado, HttpStatus.CREATED);
		}

		// TODO abstração mais simples para renderizar tudo isso!
		// TODO retorno em rest, deve ter algum padrão ou algo do tipo.
		return ResponseEntity
				.badRequest()
				.body(errors
						.getAllErrors()
						.stream()
						.map(msg -> {
							return "O campo " + msg.getObjectName() + " - " + msg.getDefaultMessage();
							}
						)
						.collect(Collectors.joining(", "))
					);
	}

	@PutMapping("/{id}")
	@ResponseBody
	public Cliente update(@Valid @PathVariable("id") Long id, @RequestBody Cliente cliente, Errors errors) {
		
		
		return this.clienteService.update(id, cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		this.clienteService.delete(id);
	}

}
