package net.gupisoft.iuris.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.Usuario;
import net.gupisoft.iuris.domain.repository.PessoaRepository;
import net.gupisoft.iuris.domain.repository.UsuarioRepository;


@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Pessoa> pessoaOptional = pessoaRepository.findByEmail(email);
		
		Pessoa pessoa = pessoaOptional.orElseThrow(() -> new UsernameNotFoundException("Email e/ou senha incorretos!"));
		
		return new UsuarioSistema(pessoa.getUsuario(), getPermissoes(pessoa.getUsuario()));
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		List<String> permissoes = usuarioRepository.permissoes(usuario);
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority("ROLE_" + p.toUpperCase())));
		
		return authorities;
	}

}
