package net.gupisoft.iuris.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Parcela;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Integer>{

}
