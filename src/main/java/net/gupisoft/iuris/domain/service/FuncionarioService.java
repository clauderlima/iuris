package net.gupisoft.iuris.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.enumeration.CargoFuncionario;
import net.gupisoft.iuris.domain.repository.PessoaRepository;


@Service
public class FuncionarioService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> listarConsultores() {
		List<Pessoa> consultores = pessoaRepository.findByCargoFuncionario(CargoFuncionario.CONSULTOR);
		return consultores;
	}

	public List<Pessoa> listarAdvogados() {
		List<Pessoa> advogados = pessoaRepository.findByCargoFuncionario(CargoFuncionario.ADVOGADO);
		return advogados;
	}

//	@Autowired
//	private FuncionarioRepository funcionarioRepository;
//
//	public List<Funcionario> listar() {
//		return funcionarioRepository.findAll();
//	}
//
//	public String incluir(Funcionario funcionario) {
//		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByCpf(funcionario.getCpf());
//		if (funcionarioOptional.isPresent() && funcionario.isNovo()) {
//			throw new ClienteJaCadastradoException("Já existe um funcionário com esse Nome");
//		}
//		
//		
//		String msg = null;
//		
//		if (funcionario.isNovo()) {
//			msg = "Tipo novo incluído com sucesso!";
//		} else {
//			msg = "Funcionário <i>" + funcionario.getNome() + "</i> alterado com sucesso!";
//		}
//
//		funcionarioRepository.save(funcionario);
//		
//		return msg;
//	}
//
//	public String remover(Funcionario funcionario) {
//		
//		System.out.println(funcionario.toString());
//		
//		String msg = "Funcionário <i>" + funcionario.getNome() + "</i> removido com sucesso!";
//		
//		funcionarioRepository.deleteById(funcionario.getId());;
//		
//		return msg;
//	}
//
//	public Funcionario busca(Integer id) {
//		return funcionarioRepository.getOne(id);
//	}
//
//	public List<Funcionario> listarGerentes(String tipo) {
//		return funcionarioRepository.findAll();
//	}
	
	
}
