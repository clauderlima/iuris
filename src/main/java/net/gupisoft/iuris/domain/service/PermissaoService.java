package net.gupisoft.iuris.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Permissao;
import net.gupisoft.iuris.domain.repository.PermissaoRepository;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public List<Permissao> listar() {
		return permissaoRepository.findAll();
	}

	public Permissao buscar(Integer id) {
		return permissaoRepository.getOne(id);
	}
	
	public String gravar(Permissao permissao) {
		
		String msg;
		
		if (permissao.getId() == null) {
			msg = "Permissao incluída com sucesso!";			
		} else {
			msg = "Permissao " + permissao.getNomePermissao() + " alterada com sucesso!";
		}
		
		Optional<Permissao> permissaoOptional = permissaoRepository.findByNomePermissao(permissao.getNomePermissao());
		if (permissaoOptional.isPresent()) {
			throw new ElementoJaCadastradoException("Essa permissão já existe");
		}
		
		permissaoRepository.saveAndFlush(permissao);
		
		return msg;
	}
	
}
