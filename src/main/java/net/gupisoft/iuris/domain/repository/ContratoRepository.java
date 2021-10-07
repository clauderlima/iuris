package net.gupisoft.iuris.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

}
