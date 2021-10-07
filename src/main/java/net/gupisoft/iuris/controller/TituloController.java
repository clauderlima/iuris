package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Titulo;
import net.gupisoft.iuris.domain.service.TituloService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/gerencia/titulo")
public class TituloController {
	
	@Autowired
	private TituloService tituloService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_TITULO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/TituloListar");
		
		mv.addObject("titulos", tituloService.listar());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured("ROLE_TITULO_INCLUIR")
	public ModelAndView novo(Titulo titulo) {
		ModelAndView mv = formulario(titulo);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_TITULO_INCLUIR")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView mv = formulario(tituloService.buscar(id));
		return mv;
	}
	
	
	// Incluir
	@PostMapping
	public ResponseEntity<?> incluir(@RequestBody @Valid Titulo titulo, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nomeTitulo").getDefaultMessage());
		}
		
		
		try {
			titulo = tituloService.incluir(titulo);			
		} catch (ElementoJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(titulo);
	}
	
	// Incluir
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Titulo titulo, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(titulo);
		}
		
		try {
			tituloService.incluir(titulo);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("nomeTitulo", e.getMessage(), e.getMessage());
			return novo(titulo);
		}
		
		attributes.addFlashAttribute("mensagem"," Escritorio incluído com sucesso!");
		return new ModelAndView("redirect:/gerencia/titulo");
	}
	
	// Formulário
	private ModelAndView formulario(Titulo titulo){
		ModelAndView mv = new ModelAndView("sistema/TituloForm");
        mv.addObject("titulo", titulo);
		return mv; 
	}
}
