package net.gupisoft.iuris.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.Captacao;
import net.gupisoft.iuris.domain.entity.HistoricoContato;

@Repository
public interface HistoricoContatoRepository extends JpaRepository<HistoricoContato, Integer>{

	public List<HistoricoContato> findByCliente(Pessoa cliente);
	
	public List<HistoricoContato> findByClienteOrderByDataHoraEventoDesc(Pessoa cliente);

	public List<HistoricoContato> findByCaptacaoOrderByDataHoraEventoDesc(Captacao captacao);

}
