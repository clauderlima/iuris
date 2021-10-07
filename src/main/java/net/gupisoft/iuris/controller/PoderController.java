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

import net.gupisoft.iuris.domain.entity.Poder;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPoder;
import net.gupisoft.iuris.domain.service.PoderService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/gerencia/poder")
public class PoderController {
	
	@Autowired
	private PoderService poderService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_TITULO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/PoderListar");
		
		mv.addObject("poderes", poderService.listar());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured("ROLE_TITULO_INCLUIR")
	public ModelAndView novo(Poder poder) {
		ModelAndView mv = formulario(poder);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_TITULO_INCLUIR")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView mv = formulario(poderService.buscar(id));
		return mv;
	}
	
	
	// Incluir
	@PostMapping
	public ResponseEntity<?> incluir(@RequestBody @Valid Poder poder, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nomePoder").getDefaultMessage());
		}
		
		
		try {
			poder = poderService.incluir(poder);			
		} catch (ElementoJaCadastradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(poder);
	}
	
	// Incluir
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Poder poder, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(poder);
		}
		
		try {
			poderService.incluir(poder);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("nomePoder", e.getMessage(), e.getMessage());
			return novo(poder);
		}
		
		attributes.addFlashAttribute("mensagem"," Escritorio incluído com sucesso!");
		return new ModelAndView("redirect:/gerencia/poder");
	}
	
	// Formulário
	private ModelAndView formulario(Poder poder){
		ModelAndView mv = new ModelAndView("sistema/PoderForm");
        mv.addObject("poderes", TipoPoder.values());
        mv.addObject("poder", poder);
		return mv; 
	}
}
