package com.tsalogistica.logistica.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsalogistica.logistica.domain.model.Cliente;
import com.tsalogistica.logistica.repository.ClienteRepository;
import com.tsalogistica.logistica.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
     
	@Autowired
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
	
	@GetMapping("{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
			
		}
		
		return ResponseEntity.notFound().build();
	} 
	
	
	
	@PostMapping
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		 return catalogoClienteService.salvar(cliente);
		 
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente) {
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
			
		}
		
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	   }
	
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		
	   }
	    
	   catalogoClienteService.excluir(clienteId);
	   
	   return ResponseEntity.noContent().build();
	
       }
	
	
			
   }
