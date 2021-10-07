package net.gupisoft.iuris.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Poder;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPoder;


@Repository
public interface PoderRepository extends JpaRepository<Poder, Integer>{

	Optional<Poder> findByDescricao(String nomePoder);
	
	List<Poder> findByTipoPoder(TipoPoder geral);

}
