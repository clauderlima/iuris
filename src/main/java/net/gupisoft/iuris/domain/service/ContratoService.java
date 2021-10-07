package net.gupisoft.iuris.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Contrato;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.repository.ContratoRepository;
import net.gupisoft.iuris.domain.repository.PessoaRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private PessoaRepository clienteRepository;

	public List<Contrato> listar() {
		return contratoRepository.findAll();
	}

	public String incluir(Contrato contrato) {
		
		String msg;
		
		if (contrato.isNovo()) {
			contrato.getCliente().setContrato(contrato); 	
			
			msg = "Contrato incluído com sucesso!";
			
		} else {
			msg = "Contrato <i>" + contrato.getNumero() + "</i> alterado com sucesso!";
		}
		

		//Contrato novoContrato = contratoRepository.save(contrato);
				
			
		contratoRepository.save(contrato);

		
		return msg;
	}
	
public String editar(Contrato contrato) {
		
		String msg =  "Contrato <i>" + contrato.getNumero() + "</i> alterado com sucesso!";

		System.out.println(contrato.toString());
		

		contratoRepository.save(contrato);
		
		return msg;
	}

	public Pessoa buscaCliente(Contrato contrato) {
		return clienteRepository.findByContrato(contrato);
	}

	public Long qtdContratos() {
		return contratoRepository.count();
	}
	
	
}
