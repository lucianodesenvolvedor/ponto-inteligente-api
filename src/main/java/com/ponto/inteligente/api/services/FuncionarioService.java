package com.ponto.inteligente.api.services;

import java.util.Optional;

import com.ponto.inteligente.api.entities.Funcionario;

public interface FuncionarioService {

	/**
	 * Persiste um funcionário na base de dados.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistir(Funcionario funcionario);

	/**
	 * Retorna um funcionário por CPF.
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);

	/**
	 * Retorna um funcionário por email.
	 * 
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorEmail(String email);

	/**
	 * Retorna um funcionário por id.
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorId(Long id);
}
