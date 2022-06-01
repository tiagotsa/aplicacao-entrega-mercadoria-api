package com.tsalogistica.logistica.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tsalogistica.logistica.domain.model.Cliente;
import com.tsalogistica.logistica.domain.model.Entrega;
import com.tsalogistica.logistica.domain.model.StatusEntrega;
import com.tsalogistica.logistica.exception.NegocioException;
import com.tsalogistica.logistica.repository.ClienteRepository;
import com.tsalogistica.logistica.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteRepository.findById(entrega.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	     
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
