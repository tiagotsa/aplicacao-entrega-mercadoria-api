package com.tsalogistica.logistica.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tsalogistica.logistica.domain.model.Entrega;
import com.tsalogistica.logistica.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	BuscaEntregaService buscaEntregaService;
	EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
	entrega.finalizar();
	
	entregaRepository.save(entrega);
	
}

}