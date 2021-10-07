package net.gupisoft.iuris.domain.repository.helper.pessoa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PessoaRepositoryImpl implements PessoaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public int clientesComContratos() {
		String query = "from Pessoa where tipo_pessoa=0 and contrato_id<>null";
		return manager.createQuery(query).getResultList().size();
	}

	@Override
	public int clientesSemContratos() {
		String query = "from Pessoa where tipo_pessoa=0 and contrato_id=null";
		return manager.createQuery(query).getResultList().size();
	}
	

}
