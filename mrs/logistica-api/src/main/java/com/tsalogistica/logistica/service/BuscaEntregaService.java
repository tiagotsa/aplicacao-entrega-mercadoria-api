package com.tsalogistica.logistica.service;

import org.springframework.stereotype.Service;

import com.tsalogistica.logistica.domain.model.Entrega;
import com.tsalogistica.logistica.exception.EntidadeNaoEncontradaException;
import com.tsalogistica.logistica.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository EntregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return EntregaRepository.findById(entregaId)
	             .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}
