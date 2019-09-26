package com.ponto.inteligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ponto.inteligente.api.entities.Lancamento;
import com.ponto.inteligente.api.repositories.LancamentoRepository;
import com.ponto.inteligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest){
		log.info("Buscando lançamentos para o funcionário Id {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}
	
	public Optional<Lancamento> buscarPorId(Long id){
		log.info("Buscando lançamentos por Id {}", id);
		return this.lancamentoRepository.findById(id);
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo lançamento {}", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public void remove(Long id) {
		log.info("Removendo lançamento por Id {}", id);
		this.lancamentoRepository.deleteById(id);;
		
	}
}
