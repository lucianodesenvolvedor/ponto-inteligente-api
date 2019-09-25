package com.ponto.inteligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ponto.inteligente.api.entities.Empresa;
import com.ponto.inteligente.api.entities.Funcionario;
import com.ponto.inteligente.api.entities.Lancamento;
import com.ponto.inteligente.api.enums.PerfilEnum;
import com.ponto.inteligente.api.enums.TipoEnum;
import com.ponto.inteligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	private Long funcionarioId;

	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();

		this.lancamentoRepository.save(obterDadosLancamento(funcionario));
		this.lancamentoRepository.save(obterDadosLancamento(funcionario));
	}

	@After
	public final void tearDown() throws Exception {
		this.empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarLancamentoPorId() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarlancamentosPorFuncionarioPaginado() {
		
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		assertEquals(2, lancamentos.getNumberOfElements());
	}
	
	
	public Lancamento obterDadosLancamento(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setFuncionario(funcionario);
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setLocalizacao("Programação");
		lancamento.setDescricao("Horário de inicio do almoço");
		return lancamento;
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fulano de Tal");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		funcionario.setCpf("24291173474");
		funcionario.setEmail("email@email.com");
		funcionario.setQtdHorasAlmoco(1.00f);
		funcionario.setQtdHorasTrabalhoDia(9.00f);
		funcionario.setValorHora(new BigDecimal("10.45"));
		funcionario.setEmpresa(empresa);
		return funcionario;
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpj("51463645000100");
		return empresa;
	}
}
