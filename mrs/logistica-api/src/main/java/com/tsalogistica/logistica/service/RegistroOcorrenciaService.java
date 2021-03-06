package com.tsalogistica.logistica.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsalogistica.logistica.domain.model.Entrega;
import com.tsalogistica.logistica.domain.model.Ocorrencia;
//import com.tsalogistica.logistica.exception.NegocioException;
//import com.tsalogistica.logistica.repository.EntregaRepository;
//import com.tsalogistica.logistica.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService  buscaEntregaService;
	//private EntregaRepository entregaRepository;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return entrega.adicionarOcorrencia(descricao);
		
	}
}
