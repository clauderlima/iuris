package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Permissao;
import net.gupisoft.iuris.domain.service.PermissaoService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/gerencia/permissao")
public class PermissaoController {
	
	@Autowired
	private PermissaoService permissaoService;

	// Listar registros
	@GetMapping
	@Secured({"ROLE_PERMISSAO_VER"})
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/Permissao");
		mv.addObject("permissao", new Permissao());
		mv.addObject("permissoes", permissaoService.listar());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured({"ROLE_PERMISSAO_INCLUIR"})
	public ModelAndView novo(Permissao permissao) {
		ModelAndView mv = formulario(permissao);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured({"ROLE_PERMISSAO_INCLUIR"})
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView mv = formulario(permissaoService.buscar(id));
		return mv;
	}
	
	// Incluir
	@PostMapping("/salvar")
	@Secured({"ROLE_PERMISSAO_INCLUIR"})
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result, RedirectAttributes attributes) {
		
		String msg = null;

		if (result.hasErrors()) {
			return novo(permissao);
		}
		
		try {
			msg = permissaoService.gravar(permissao);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("nomePermissao", e.getMessage(), e.getMessage());
			return novo(permissao);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		return new ModelAndView("redirect:/gerencia/permissao");
	}
	
	// Formulário
	private ModelAndView formulario(Permissao permissao){
		ModelAndView mv = new ModelAndView("sistema/Permissao");
		mv.addObject("permissoes", permissaoService.listar());
        mv.addObject("permissao", permissao);
		return mv; 
	}
}
