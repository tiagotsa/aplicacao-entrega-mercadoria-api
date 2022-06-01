package com.tsalogistica.logistica.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tsalogistica.logistica.domain.model.Cliente;
import com.tsalogistica.logistica.exception.NegocioException;
import com.tsalogistica.logistica.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
		}
			
		return clienteRepository.save(cliente); 
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
