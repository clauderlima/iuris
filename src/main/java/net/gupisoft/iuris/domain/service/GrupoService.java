package net.gupisoft.iuris.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Grupo;
import net.gupisoft.iuris.domain.repository.GrupoRepository;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	public List<Grupo> listar() {
		return grupoRepository.findAll();
	}

	public String gravar(Grupo grupo) {
		String msg;
		
		if (grupo.getId() == null) {
			msg = "Permissao incluída com sucesso!";			
		} else {
			msg = "Grupo " + grupo.getNomeGrupo() + " alterado com sucesso!";
		}
		
		Optional<Grupo> grupoOptional = grupoRepository.findByNomeGrupo(grupo.getNomeGrupo());
		if (grupoOptional.isPresent() && grupo.getId() == null) {
			throw new ElementoJaCadastradoException("Esse grupo já existe");
		}
		
		grupoRepository.saveAndFlush(grupo);
		
		return msg;
	}

	public Grupo buscar(Integer id) {
		return grupoRepository.getOne(id);
	}
	
}
