package net.gupisoft.iuris.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Escritorio;
import net.gupisoft.iuris.domain.repository.EscritorioRepository;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Service
public class EscritorioService {

	@Autowired
	private EscritorioRepository escritorioRepository;
	
	public void incluir(Escritorio escritorio) {
		Optional<Escritorio> escritorioOptional = escritorioRepository.findByCnpj(escritorio.getCnpj());
		if (escritorioOptional.isPresent() && escritorio.isNovo()) {
			throw new ElementoJaCadastradoException("Já existe um escritorio com esse CNPJ");
		}
		escritorioRepository.save(escritorio);
	}

	public List<Escritorio> listar() {
		return escritorioRepository.findAll();
	}

	public Escritorio buscar(Integer id) {
		return escritorioRepository.getOne(id);
	}

	public long qtdEscritorios() {
		return escritorioRepository.count();
	}

}
