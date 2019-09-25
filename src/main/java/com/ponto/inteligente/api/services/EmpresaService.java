package com.ponto.inteligente.api.services;

import java.util.Optional;

import com.ponto.inteligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 *  Retorna uma empresa dado um CNPJ
	 *  
	 *  @param cnpj
	 *  @Return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
}
