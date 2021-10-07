package net.gupisoft.iuris.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.gupisoft.iuris.domain.entity.Usuario;
import net.gupisoft.iuris.domain.repository.UsuarioRepository;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	public String salvar(Usuario usuario) {
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new ElementoJaCadastradoException("Senha obrigatória para novo usuário");
		}
		
		
		
		if (usuario.isNovo() || usuario.getSenha() != "") {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
	
		usuarioRepository.save(usuario);
		return "Alterações realizadas com sucesso!";
	}
}
