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

import net.gupisoft.iuris.domain.entity.Grupo;
import net.gupisoft.iuris.domain.service.GrupoService;
import net.gupisoft.iuris.domain.service.PermissaoService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/gerencia/grupo")
public class GrupoController {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoService permissaoService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_GRUPO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/GrupoListar");
		mv.addObject("grupo", new Grupo());
		mv.addObject("grupos", grupoService.listar());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured({"ROLE_GRUPO_INCLUIR"})
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = formulario(grupo);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_GRUPO_INCLUIR")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView mv = formulario(grupoService.buscar(id));
		return mv;
	}
	
	// Incluir
	@PostMapping("/salvar")
	@Secured("ROLE_GRUPO_INCLUIR")
	public ModelAndView salvar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes) {
		
		String msg = null;

		if (result.hasErrors()) {
			return novo(grupo);
		}
		
		try {
			msg = grupoService.gravar(grupo);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("nomeGrupo", e.getMessage(), e.getMessage());
			return novo(grupo);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		return new ModelAndView("redirect:/gerencia/grupo");
	}
	
	// Formulário
	private ModelAndView formulario(Grupo grupo){
		ModelAndView mv = new ModelAndView("sistema/GrupoForm");
		mv.addObject("permissoes", permissaoService.listar());
        mv.addObject("grupo", grupo);
		return mv; 
	}
}
