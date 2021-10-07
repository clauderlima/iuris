package net.gupisoft.iuris.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Procuracao;

@Repository
public interface ProcuracaoRepository extends JpaRepository<Procuracao, Integer> {

}
