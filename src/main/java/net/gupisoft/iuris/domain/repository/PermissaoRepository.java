package net.gupisoft.iuris.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {

	Optional<Permissao> findByNomePermissao(String nomePermissao);

}
