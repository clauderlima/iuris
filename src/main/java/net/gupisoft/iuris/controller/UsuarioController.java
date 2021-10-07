package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.Usuario;
import net.gupisoft.iuris.domain.service.GrupoService;
import net.gupisoft.iuris.domain.service.PessoaService;
import net.gupisoft.iuris.domain.service.UsuarioService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/gerencia/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private GrupoService grupoService;
	
	@GetMapping
	@Secured("ROLE_USUARIO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/UsuarioListar");
		
		mv.addObject("pessoas", pessoaService.listarPessoas());
		
		return mv;
	}
	
	@GetMapping("/{id}")
	@Secured("ROLE_USUARIO_INCLUIR")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		
		Pessoa pessoa = pessoaService.buscarPessoa(id);
		
		Usuario usuario = new Usuario();
		
		if (pessoa.getUsuario() == null) {
			usuario.setPessoa(pessoa);
		} else {
			usuario = pessoa.getUsuario();
		}

		return formulario(usuario);
	}
	

	
	// Incluir
	@RequestMapping(value = {"", "{\\d+}"}, method = RequestMethod.POST)
	@Secured("ROLE_USUARIO_INCLUIR")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		
		try {
			msg = usuarioService.salvar(usuario);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return formulario(usuario);
		} 
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/gerencia/usuario");
	}

	
	private ModelAndView formulario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("sistema/UsuarioForm");
		
		mv.addObject("usuario", usuario);
		mv.addObject("grupos", grupoService.listar());
		
		return mv;
	}
}
