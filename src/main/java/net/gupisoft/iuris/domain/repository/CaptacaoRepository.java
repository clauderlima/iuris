package net.gupisoft.iuris.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Captacao;

@Repository
public interface CaptacaoRepository extends JpaRepository<Captacao, Integer>{

	Optional<Captacao> findByTelefone(String telefone);

}
