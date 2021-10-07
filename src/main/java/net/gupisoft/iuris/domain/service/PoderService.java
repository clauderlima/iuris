package net.gupisoft.iuris.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Poder;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPoder;
import net.gupisoft.iuris.domain.repository.PoderRepository;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;

@Service
public class PoderService {

	@Autowired
	private PoderRepository poderRepository;

	public List<Poder> listar() {
		return poderRepository.findAll();
	}

	public Poder buscar(Integer id) {
		return poderRepository.getOne(id);
	}

	public Poder incluir(Poder poder) {
		Optional<Poder> poderOptional = poderRepository.findByDescricao(poder.getDescricao());
		if (poderOptional.isPresent() && poder.isNovo()) {
			throw new ElementoJaCadastradoException("Esse assunto já exite");
		}
		return poderRepository.saveAndFlush(poder);
		
	}

	public List<Poder> buscarGeral() {
		return poderRepository.findByTipoPoder(TipoPoder.geral);
	}

	public List<Poder> buscaEspeciais() {
		return poderRepository.findByTipoPoder(TipoPoder.especifico);
	}
	
	
}
