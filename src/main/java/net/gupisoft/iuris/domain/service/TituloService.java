package net.gupisoft.iuris.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Titulo;
import net.gupisoft.iuris.domain.repository.TituloRepository;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;

@Service
public class TituloService {

	@Autowired
	private TituloRepository tituloRepository;

	public List<Titulo> listar() {
		return tituloRepository.findAll();
	}

	public Titulo buscar(Integer id) {
		return tituloRepository.getOne(id);
	}

	public Titulo incluir(Titulo titulo) {
		Optional<Titulo> tituloOptional = tituloRepository.findByNomeTitulo(titulo.getNomeTitulo());
		if (tituloOptional.isPresent() && titulo.isNovo()) {
			throw new ElementoJaCadastradoException("Esse assunto já exite");
		}
		return tituloRepository.saveAndFlush(titulo);
		
	}
	
	
}
