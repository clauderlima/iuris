package net.gupisoft.iuris.domain.repository.helper.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.gupisoft.iuris.domain.entity.Usuario;


public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery(
			"select distinct p.nomePermissao from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
			.setParameter("usuario", usuario)
			.getResultList();
	}

}
