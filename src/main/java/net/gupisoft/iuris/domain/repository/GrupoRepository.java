package net.gupisoft.iuris.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

	Optional<Grupo> findByNomeGrupo(String nome);

}
