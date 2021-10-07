package net.gupisoft.iuris.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Captacao;
import net.gupisoft.iuris.domain.repository.CaptacaoRepository;
import net.gupisoft.iuris.domain.repository.HistoricoContatoRepository;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Service
public class CaptacaoService {

	@Autowired
	private CaptacaoRepository captacaoRepository;
	
	@Autowired
	private HistoricoContatoRepository historicoRepository;

	public String gravar(Captacao captacao) {
		String msg;
		
		if (captacao.getId() == null) {
			msg = "Cliente incluído com sucesso!";			
		} else {
			msg = "Cliente " + captacao.getNome() + " alterado com sucesso!";
		}
		
		captacaoRepository.saveAndFlush(captacao);
		
		return msg;
	}

	public List<Captacao> listar() {
		return captacaoRepository.findAll();
	}

	public Object buscarPessoa(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object buscarHistoricos(Captacao captacao) {
		return historicoRepository.findByCaptacaoOrderByDataHoraEventoDesc(captacao);
	}

	public String incluir(@Valid Captacao captacao) {
		Optional<Captacao> captacaoOptional = captacaoRepository.findByTelefone(captacao.getTelefone());
		if (captacaoOptional.isPresent() && captacao.isNovo()) {
			throw new ElementoJaCadastradoException("Já existe um cliente com esse Telefone");
		}
		
		
		String msg = null;
		
		if (captacao.isNovo()) {
			msg = "Inclusão realizada com sucesso!";
		} else {
			msg = "Edição de <i>" + captacao.getNome() + "</i> realizadao com sucesso!";
		}

		captacaoRepository.save(captacao);
		
		return msg;
	}

	public Captacao buscar(Integer id) {
		return captacaoRepository.getOne(id);
	}
	
	
}
