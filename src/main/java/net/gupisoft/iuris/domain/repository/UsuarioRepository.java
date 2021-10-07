package net.gupisoft.iuris.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gupisoft.iuris.domain.entity.Usuario;
import net.gupisoft.iuris.domain.repository.helper.usuario.UsuarioRepositoryQueries;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, UsuarioRepositoryQueries {

}
