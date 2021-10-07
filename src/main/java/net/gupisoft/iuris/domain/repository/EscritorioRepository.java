package net.gupisoft.iuris.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Escritorio;

@Repository
public interface EscritorioRepository extends JpaRepository<Escritorio, Integer> {

	public Optional<Escritorio> findByCnpj(String cnpj);
}
