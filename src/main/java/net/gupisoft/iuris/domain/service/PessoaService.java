package net.gupisoft.iuris.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.HistoricoContato;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPessoa;
import net.gupisoft.iuris.domain.repository.HistoricoContatoRepository;
import net.gupisoft.iuris.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private HistoricoContatoRepository historicoContatoRepository;
	
	public String incluir(Pessoa cliente) {
//		Optional<Pessoa> clienteOptional = pessoaRepository.findByCpf(cliente.getCpf());
		
//		if (clienteOptional.isPresent() && cliente.isNovo()) {
//			throw new ClienteJaCadastradoException("Já existe uma pessoa no sistema com esse CPF");
//		}
		
		
		String msg = null;
		
		if (cliente.isNovo()) {
			msg = "Inclusão realizada com sucesso!";
		} else {
			msg = "Edição de <i>" + cliente.getNome() + "</i> realizadao com sucesso!";
		}

		pessoaRepository.save(cliente);
		
		return msg;
	}

	public List<Pessoa> listarClientes() {
		return pessoaRepository.findByTipoPessoa(TipoPessoa.cliente);
	}

	public Pessoa buscarPessoa(Integer id) {
		return pessoaRepository.getOne(id);
	}

	public long qtdClientesSemContrato() {
		return pessoaRepository.clientesSemContratos();
	}
	
	public long qtdClientesComContrato() {
		return pessoaRepository.clientesComContratos();
	}

	public List<HistoricoContato> buscarHistoricos(Pessoa cliente) {
		return historicoContatoRepository.findByClienteOrderByDataHoraEventoDesc(cliente);
	}

	public List<Pessoa> listarFuncionarios() {
		return pessoaRepository.findByTipoPessoa(TipoPessoa.funcionario);
	}

	public String removeFuncionario(Pessoa funcionario) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}
}
