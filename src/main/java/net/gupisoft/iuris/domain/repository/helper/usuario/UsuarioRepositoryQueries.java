package net.gupisoft.iuris.domain.repository.helper.usuario;

import java.util.List;

import net.gupisoft.iuris.domain.entity.Usuario;

public interface UsuarioRepositoryQueries {

	public List<String> permissoes(Usuario usuario);
	
}
