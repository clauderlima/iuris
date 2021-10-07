package net.gupisoft.iuris.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Procuracao;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPoder;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.repository.PessoaRepository;
import net.gupisoft.iuris.domain.repository.PoderRepository;
import net.gupisoft.iuris.domain.repository.ProcuracaoRepository;

@Service
public class ProcuracaoService {

	@Autowired
	private ProcuracaoRepository procuracaoRepository;
	
	@Autowired
	private PessoaRepository clienteRepository;
	
	@Autowired
	private PoderRepository poderRepository;

	public List<Procuracao> listar() {
		return procuracaoRepository.findAll();
	}

	public String incluir(Procuracao procuracao) {
		
		String msg;
		
		if (procuracao.isNovo()) {
			procuracao.getCliente().setProcuracao(procuracao); 	
			
			msg = "Procuração incluída com sucesso!";
			
		} else {
			msg = "Procuração alterada com sucesso!";
		}
		

		//Procuracao novoProcuracao = procuracaoRepository.save(procuracao);
				
			
		procuracaoRepository.save(procuracao);

		
		return msg;
	}
	
public String editar(Procuracao procuracao) {
		
		String msg =  "Procuração alterada com sucesso!";

		System.out.println(procuracao.toString());
		

		procuracaoRepository.save(procuracao);
		
		return msg;
	}

	public Pessoa buscaCliente(Procuracao procuracao) {
		return clienteRepository.findByProcuracao(procuracao);
	}

	public Long qtdProcuracoes() {
		return procuracaoRepository.count();
	}

	public Procuracao buscar(Integer id) {
		return procuracaoRepository.getOne(id);
	}
	
	public String buscarPoderGeral() {
		return poderRepository.findByTipoPoder(TipoPoder.geral).toString();
	}
	
}
