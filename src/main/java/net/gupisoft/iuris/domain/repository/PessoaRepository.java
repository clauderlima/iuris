package net.gupisoft.iuris.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Contrato;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.Procuracao;
import net.gupisoft.iuris.domain.entity.enumeration.CargoFuncionario;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPessoa;
import net.gupisoft.iuris.domain.repository.helper.pessoa.PessoaRepositoryQueries;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, PessoaRepositoryQueries {

	public Optional<Pessoa> findByCpf(String cpf);

	public Pessoa findByContrato(Contrato contrato);
	
	public Optional<Pessoa> findByEmail(String email);
	
	public List<Pessoa> findByTipoPessoa(TipoPessoa tipoPessoa);

	public Pessoa findByProcuracao(Procuracao procuracao);

	public List<Pessoa> findByCargoFuncionario(CargoFuncionario advogado);
	
	
}
