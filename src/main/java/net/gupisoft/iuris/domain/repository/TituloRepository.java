package net.gupisoft.iuris.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Titulo;


@Repository
public interface TituloRepository extends JpaRepository<Titulo, Integer>{

	Optional<Titulo> findByNomeTitulo(String nomeTitulo);
	

}
